package in.epaylater.testApp.service;

import in.epaylater.testApp.dao.CreditDataDao;
import in.epaylater.testApp.dao.TokenDataDao;
import in.epaylater.testApp.dao.TransactionDataDao;
import in.epaylater.testApp.entity.CreditLimitData;
import in.epaylater.testApp.entity.TokenData;
import in.epaylater.testApp.entity.TransactionData;
import in.epaylater.testApp.model.ResponseEntity;
import in.epaylater.testApp.model.TransactionDetails;
import org.eclipse.jetty.util.StringUtil;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TestAppService {

    private static final Logger log = LoggerFactory.getLogger("testApp");
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    private TokenDataDao tokenDataDao;
    private CreditDataDao creditDataDao;
    private TransactionDataDao transactionDataDao;

    @Inject
    public TestAppService(final TokenDataDao tokenDataDao,
                          final CreditDataDao creditDataDao,
                          final TransactionDataDao transactionDataDao) {
        this.tokenDataDao = tokenDataDao;
        this.creditDataDao = creditDataDao;
        this.transactionDataDao = transactionDataDao;
    }

    public ResponseEntity loginToken(String phoneNumber) {
        String token = UUID.randomUUID().toString();
        TokenData tokenData = new TokenData(null, phoneNumber, token);
        tokenDataDao.insertOrUpdate(tokenData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("phoneNumber", phoneNumber);

        ResponseEntity responseEntity = new ResponseEntity(SUCCESS, jsonObject);

        return responseEntity;
    }

    public ResponseEntity spend(TransactionDetails transactionDetails, String authToken) {
        String phoneNumber = transactionDetails.getPhone();
        Double transactionAmount = transactionDetails.getAmount();
        String transactionDateString = transactionDetails.getDate();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date transactionDate = null;

        try {
            transactionDate = formatter.parse(transactionDateString);
        } catch (ParseException e) {
            log.info("parse Exceptoion in date.");
            return prepareFailureResponse("Invalid date", "unparseble date in transaction");
        }

        TokenData tokenData = tokenDataDao.findTokenDataByPhoneNumber(phoneNumber);
        if(tokenData == null){
            return prepareFailureResponse("Invalid User",
                    "User not present in system");
        }else if (StringUtil.isBlank(tokenData.getToken()) || StringUtil.isBlank(authToken)
                || !tokenData.getToken().equalsIgnoreCase(authToken)) {
            return prepareFailureResponse("Invalid login token",
                    "Invalid login token received: " + authToken);
        }

        CreditLimitData creditLimitData = creditDataDao.findCreditLimitDataByPhoneNumber(phoneNumber);
        Double remainingCreditLimit = creditLimitData.getRemainingCreditLimit();

        if (remainingCreditLimit < transactionAmount) {
            return prepareFailureResponse("Invalid amount",
                    "transaction amount (" + transactionAmount + ") " +
                            "is more than remaining credit limit (" + remainingCreditLimit + ")");
        }

        Double remainingCreditLimitBeforeTransaction = creditLimitData.getRemainingCreditLimit();
        Double remainingCreditLimitAfterTransaction = remainingCreditLimitBeforeTransaction - transactionAmount;
        CreditLimitData creditLimitDataNew = new CreditLimitData(null, phoneNumber,
                creditLimitData.getCreditLimit(), remainingCreditLimitAfterTransaction);

        TransactionData transactionData = new TransactionData(null, phoneNumber, transactionDate,
                transactionDetails.getDescription(), transactionAmount);

        creditDataDao.insertOrUpdate(creditLimitDataNew);
        Long transactionID = transactionDataDao.create(transactionData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transaction id", transactionID);

        return new ResponseEntity(SUCCESS, jsonObject);

    }

    private ResponseEntity prepareFailureResponse(String error, String errorMessage) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", error);
        jsonObject.put("errorMessage", errorMessage);

        return new ResponseEntity(FAILURE, jsonObject);
    }
}

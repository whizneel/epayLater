package in.epaylater.testApp.dao;

import in.epaylater.testApp.entity.CreditLimitData;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

@RegisterMapper(CreditDataMapper.class)
public interface CreditDataDao {

    @SqlUpdate("INSERT INTO epaylater.credit_data (phone, credit_limit_original, credit_limit_remaining) VALUES (:phone, :creditLimit, :remainingCreditLimit) on duplicate key update credit_limit_remaining = :remainingCreditLimit")
    @GetGeneratedKeys
    Long insertOrUpdate(@BindBean CreditLimitData creditLimitData);

    //TODO:check why Single value result not working
    @SqlQuery("SELECT * FROM credit_data where credit_data.phone = :phone")
    //@SingleValueResult
    CreditLimitData findCreditLimitDataByPhoneNumber(@Bind("phone") String phone);

}
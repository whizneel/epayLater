package in.epaylater.testApp.server;


import com.google.inject.AbstractModule;
import in.epaylater.testApp.dao.CreditDataDao;
import in.epaylater.testApp.dao.TokenDataDao;
import in.epaylater.testApp.dao.TransactionDataDao;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAppModule extends AbstractModule {
    private static final Logger log = LoggerFactory.getLogger("testApp");

    private final TestAppConfiguration configuration;
    private final DBI dbi;

    public TestAppModule(TestAppConfiguration configuration, DBI dbi) {
        super();
        this.configuration = configuration;
        this.dbi = dbi;
    }

    @Override
    protected void configure() {
        // bind daos
        bind(TokenDataDao.class).toInstance(dbi.onDemand(TokenDataDao.class));
        bind(CreditDataDao.class).toInstance(dbi.onDemand(CreditDataDao.class));
        bind(TransactionDataDao.class).toInstance(dbi.onDemand(TransactionDataDao.class));

    }


}

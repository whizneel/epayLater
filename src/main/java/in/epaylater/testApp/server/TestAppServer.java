package in.epaylater.testApp.server;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.Logger;
import com.codahale.metrics.servlets.PingServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import in.epaylater.testApp.controller.TestAppController;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.java8.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class TestAppServer extends Application<TestAppConfiguration> {

    public static void main(String[] args) throws Exception {
        new TestAppServer().run(args);
    }

    @Override
    public void initialize(Bootstrap<TestAppConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<TestAppConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(TestAppConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

    }

    @Override
    public String getName() {
        return "EpayLater";
    }

    @Override
    public void run(TestAppConfiguration configuration, Environment environment)
            throws Exception {
        // create dbi
        DBI dbi = new DBIFactory().build(environment, configuration.getDataSourceFactory(), "mysql");

        // create ioc
        Injector injector = createInjector(configuration, dbi);

        // resources
        environment.jersey().register(injector.getInstance(TestAppController.class));

        // heartbeat
        environment.servlets()
                .addServlet("ping", new PingServlet())
                .addMapping("/ping");

        // cors
        FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,OPTIONS");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        AsyncAppender consoleAppender = (AsyncAppender) root.getAppender("async-console-appender");
        if (consoleAppender != null) {
            consoleAppender.setIncludeCallerData(true);
        }

        AsyncAppender fileAppender = (AsyncAppender) root.getAppender("async-file-appender");
        if (fileAppender != null) {
            fileAppender.setIncludeCallerData(true);
        }
    }

    protected Injector createInjector(
            TestAppConfiguration configuration,
            DBI dbi) {
        return Guice.createInjector(
                new TestAppModule(configuration, dbi)
        );
    }
}

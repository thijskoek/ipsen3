package nl.hsleiden.ipsen3;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.hsleiden.ipsen3.dao.WijnDAO;
import nl.hsleiden.ipsen3.resources.WijnResource;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Daan on 30-Nov-15.
 */
public class App extends Application<AppConfiguration> {
    private final HibernateBundle<AppConfiguration> hibernate = new HibernateBundle


    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, appConfiguration.getDataSourceFactory(), "postgresql");
        final WijnDAO dao = jdbi.onDemand(WijnDAO.class);
        final WijnResource resource = new WijnResource(
                appConfiguration.getTemplate(),
                appConfiguration.getDefaultName(),
                dao
        );
        environment.jersey().register(resource);
    }
}

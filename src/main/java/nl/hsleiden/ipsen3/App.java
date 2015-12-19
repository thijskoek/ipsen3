package nl.hsleiden.ipsen3;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.hsleiden.ipsen3.core.Wijn;
import nl.hsleiden.ipsen3.dao.WijnDAO;
import nl.hsleiden.ipsen3.resources.WijnResource;

/**
 * Bootstrap class for the App. Most of the configuration will be set here.
 *
 * @author Daan Rosbergen
 * Created by Daan on 30-Nov-15.
 */
public class App extends Application<AppConfiguration> {
    private final HibernateBundle<AppConfiguration> hibernate = new HibernateConfiguration();

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        final WijnDAO dao = new WijnDAO(hibernate.getSessionFactory());
        final WijnResource resource = new WijnResource(
                appConfiguration.getTemplate(),
                appConfiguration.getDefaultName(),
                dao
        );
        environment.jersey().register(resource);
    }
}

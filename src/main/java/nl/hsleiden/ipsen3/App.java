package nl.hsleiden.ipsen3;

import com.codahale.metrics.MetricRegistry;
import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.hsleiden.ipsen3.config.AppConfiguration;
import nl.hsleiden.ipsen3.config.ClientFilter;
import nl.hsleiden.ipsen3.config.HibernateConfiguration;
import nl.hsleiden.ipsen3.dao.ActieDAO;
import nl.hsleiden.ipsen3.dao.BestellijstDAO;
import nl.hsleiden.ipsen3.config.MigrationsConfiguration;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.UserDAO;
import nl.hsleiden.ipsen3.dao.WijnDAO;
import nl.hsleiden.ipsen3.resource.ActieResource;
import nl.hsleiden.ipsen3.resource.MailResource;

import nl.hsleiden.ipsen3.resources.BestellijstResource;
import nl.hsleiden.ipsen3.resource.UserResource;
import nl.hsleiden.ipsen3.resource.WijnResource;
import nl.hsleiden.ipsen3.service.AuthenticationService;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Bootstrap class for the App. Most of the configuration will be set here.
 *
 * @author Daan Rosbergen
 * Created by Daan on 30-Nov-15.
 */
public class App extends Application<AppConfiguration> {
    private final HibernateBundle<AppConfiguration> hibernate = new HibernateConfiguration();
    private final MigrationsBundle<AppConfiguration> liquibase = new MigrationsConfiguration();
    private final Logger logger = LoggerFactory.getLogger(App.class);
    private final MetricRegistry metricRegistry = new MetricRegistry();

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle((ConfiguredBundle)
            new ConfiguredAssetsBundle("/bower_components/", "/client", "index.html"));
        bootstrap.addBundle(liquibase);
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        name = appConfiguration.getApiName();

        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        final WijnDAO wijnDAO = new WijnDAO(hibernate.getSessionFactory());

        enableCORS(environment);
        setupAuthentication(environment, userDAO, appConfiguration);
        configureClientFilter(environment);

        final WijnResource wijnResource = new WijnResource(wijnDAO);
        final UserResource userResource = new UserResource(userDAO);
        final MailResource mailResource = new MailResource();
        environment.jersey().register(wijnResource);
        environment.jersey().register(userResource);
        environment.jersey().register(mailResource);
    }

    /**
     * Enables CORS.
     *
     * @param environment
     */
    private void enableCORS(Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    /**
     * Setups authentication via BasicCredentials and adds a caching layer.
     *
     * @param environment
     * @param userDAO
     * @param appConfiguration
     */
    private void setupAuthentication(Environment environment, UserDAO userDAO,
        AppConfiguration appConfiguration) {
        AuthenticationService authenticationService = new AuthenticationService(userDAO);
        ApiUnauthorizedHandler unauthorizedHandler = new ApiUnauthorizedHandler();
        CachingAuthenticator<BasicCredentials, User> cachingAuthenticator =
            new CachingAuthenticator<BasicCredentials, User>(
            metricRegistry, authenticationService, appConfiguration.getAuthenticationCachePolicy()
        );

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(cachingAuthenticator)
                        .setAuthorizer(authenticationService)
                        .setRealm("SUPER SECRET STUFF")
                        .setUnauthorizedHandler(unauthorizedHandler)
                        .buildAuthFilter())
        );

        final BestellijstDAO bestellijstDao = new BestellijstDAO(hibernate.getSessionFactory());
        final BestellijstResource BResource = new BestellijstResource(bestellijstDao);
        environment.jersey().register(BResource);

        final ActieDAO actieDAO = new ActieDAO(hibernate.getSessionFactory());
        final ActieResource AResource = new ActieResource(actieDAO);
        environment.jersey().register(AResource);

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

    /**
     * Serves assets from /public_html. Used for serving Angular.
     *
     * @param environment
     */
    private void configureClientFilter(Environment environment) {
        environment.getApplicationContext().addFilter(
            new FilterHolder(new ClientFilter()),
            "/*",
            EnumSet.allOf(DispatcherType.class)
        );
    }
}

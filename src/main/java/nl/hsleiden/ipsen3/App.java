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
import nl.hsleiden.ipsen3.config.MigrationsConfiguration;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.dao.*;

import nl.hsleiden.ipsen3.resource.MailResource;
import nl.hsleiden.ipsen3.resource.UserResource;
import nl.hsleiden.ipsen3.resource.WijnResource;
import nl.hsleiden.ipsen3.resources.BestellingResource;

import nl.hsleiden.ipsen3.resource.*;
import nl.hsleiden.ipsen3.resources.BestellijstResource;

import nl.hsleiden.ipsen3.resources.GebruikersResource;
import nl.hsleiden.ipsen3.resources.WachtwoordResource;
import nl.hsleiden.ipsen3.service.AuthenticationService;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
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
    private String uploadDir;

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
        uploadDir = appConfiguration.getUploadDir();

        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        final WijnDAO wijnDAO = new WijnDAO(hibernate.getSessionFactory());
        final CompanyDAO companyDAO = new CompanyDAO(hibernate.getSessionFactory());
        final GebruikerDAO gebruikerDAO = new GebruikerDAO(hibernate.getSessionFactory());
        final SleutelDAO sleutelDAO = new SleutelDAO(hibernate.getSessionFactory());
        final BestellingDAO bestellingDAO = new BestellingDAO(hibernate.getSessionFactory());
        final FactuurDAO factuurDAO = new FactuurDAO(hibernate.getSessionFactory());
        final BestellijstDAO bestellijstDAO = new BestellijstDAO(hibernate.getSessionFactory());
        final ActieDAO actieDAO = new ActieDAO(hibernate.getSessionFactory());


        enableCORS(environment);
        setupAuthentication(environment, userDAO, appConfiguration);
        configureClientFilter(environment);


        final WijnResource wijnResource = new WijnResource(wijnDAO, uploadDir);
        final UserResource userResource = new UserResource(userDAO);
        final OrderResource orderResource = new OrderResource(factuurDAO);
        final MailResource mailResource = new MailResource();
        final WachtwoordResource wachtwoordResource = new WachtwoordResource(userDAO, sleutelDAO);
        final GebruikersResource gebruikersResource = new GebruikersResource(gebruikerDAO, userDAO);
        final BestellingResource bestellingResource = new BestellingResource(factuurDAO);
        final ActieResource actieResource = new ActieResource(actieDAO);
        final BestellijstResource bestellijstResource = new BestellijstResource(bestellijstDAO);
        final FactuurResource factuurResource = new FactuurResource(factuurDAO);
        final CompanyResource companyResource = new CompanyResource(companyDAO);




        environment.jersey().register(wijnResource);
        environment.jersey().register(userResource);
        environment.jersey().register(mailResource);
        environment.jersey().register(orderResource);
        environment.jersey().register(wachtwoordResource);
        environment.jersey().register(gebruikersResource);
        environment.jersey().register(bestellingResource);
        environment.jersey().register(actieResource);
        environment.jersey().register(bestellijstResource);
        environment.jersey().register(factuurResource);
        environment.jersey().register(companyResource);
        environment.jersey().register(MultiPartFeature.class);
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

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<User>(User.class));

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

package nl.hsleiden.ipsen3.config;

import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.ScanningHibernateBundle;

/**
 * Wrapper for Hibernate configuration to add ORM support to Dropwizard. All your models should be passsed to its super.
 *
 * @author Daan Rosbergen
 * Created by daan on 19/12/15.
 */
public class HibernateConfiguration extends ScanningHibernateBundle<AppConfiguration> {

    public HibernateConfiguration() {
        super("nl.hsleiden.ipsen3.core"); // Place all your models here for ORM support
    }

    public PooledDataSourceFactory getDataSourceFactory(AppConfiguration appConfiguration) {
        return appConfiguration.getDataSourceFactory();
    }

}

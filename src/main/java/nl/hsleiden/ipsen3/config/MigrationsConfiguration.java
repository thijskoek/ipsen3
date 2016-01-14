package nl.hsleiden.ipsen3.config;

import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;

public class MigrationsConfiguration extends MigrationsBundle<AppConfiguration> {
    @Override public PooledDataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
        return configuration.getDataSourceFactory();
    }
}

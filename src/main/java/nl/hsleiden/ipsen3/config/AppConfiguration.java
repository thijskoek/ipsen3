package nl.hsleiden.ipsen3.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.CacheBuilderSpec;
import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Daan on 30-Nov-15.
 */
public class AppConfiguration extends Configuration implements AssetsBundleConfiguration {
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @NotEmpty
    @JsonProperty
    private String apiName;

    @NotEmpty
    @JsonProperty
    private String uploadDir;

    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = new AssetsConfiguration();

    @NotEmpty
    private String authenticationCachePolicy;

    public String getApiName()
    {
        return apiName;
    }

    public void setApiName(String apiName)
    {
        this.apiName = apiName;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }

    @JsonProperty
    public CacheBuilderSpec getAuthenticationCachePolicy() {
        return CacheBuilderSpec.parse(authenticationCachePolicy);
    }

    public String getUploadDir() {

        return uploadDir;
    }
}

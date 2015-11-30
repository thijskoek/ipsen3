package nl.hsleiden.ipsen3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

/**
 * Created by Daan on 30-Nov-15.
 */
public class AppConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}

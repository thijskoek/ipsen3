package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Brandon on 12-Jan-16.
 */
public class Mail {
    private String name;
    private String email;
    private String subject;
    private String message;

    public Mail() {
    }

    public Mail(String name, String email, String subject, String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty
    public String getEmail() {
        return email;
    }
    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }
    @JsonProperty
    public String getSubject() {
        return subject;
    }
    @JsonProperty
    public void setSubject(String subject) {
        this.subject = subject;
    }
    @JsonProperty
    public String getMessage() {
        return message;
    }
    @JsonProperty
    public void setMessage(String message) {
        this.message = message;
    }
}

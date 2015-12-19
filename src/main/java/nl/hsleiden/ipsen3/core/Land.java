package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by daan on 19/12/15.
 */
@Entity
@Table(name = "land")
public class Land {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "land", nullable = false, length = 255)
    @Length(max = 255)
    private String name;

    public Land() {}

    public Land(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}

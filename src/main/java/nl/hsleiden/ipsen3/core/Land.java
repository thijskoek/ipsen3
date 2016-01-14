package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.ipsen3.View;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by daan on 19/12/15.
 */
@Entity
@Table(name = "land")
public class Land {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.Private.class)
    private long id;

    @Column(name = "land", nullable = false, length = 255, unique = true)
    @Length(max = 255)
    @JsonView(View.Public.class)
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

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }
}

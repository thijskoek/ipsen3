package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.ipsen3.View;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Daan Rosbergen
 */
@Entity
@Table(name = "role")
public class Role {

    @Id @GeneratedValue @Column(name = "id") @JsonView(View.Public.class)
    private long id;

    @JsonView(View.Public.class) @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Role() {
        // Empty constructor
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<User> getUsers()
    {
        return users;
    }

    @JsonIgnore
    public void addUser(User user)
    {
        this.users.add(user);
    }
}

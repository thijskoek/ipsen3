package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Daan Rosbergen
 */
@Entity
@Table(name = "role")
public class Role {

    @Id @GeneratedValue @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToMany
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

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}

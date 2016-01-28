package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<User>();

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

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<User> getUsers()
    {
        return users;
    }

    @JsonProperty
    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    @JsonIgnore
    public void addUser(User user)
    {
        this.users.add(user);
    }
}

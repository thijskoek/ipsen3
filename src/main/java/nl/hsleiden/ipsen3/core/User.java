package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.ipsen3.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Daan Rosbergen
 */
@Entity
@Table(name = "\"user\"")
public class User implements Principal {

    @Id @GeneratedValue @Column(name = "id")
    private long id;

    @NotEmpty @Email @JsonView(View.Public.class) @Column(name = "email")
    private String email;

    @NotEmpty @JsonView(View.Protected.class) @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role")
    @JsonView(View.Private.class)
    private Set<Role> roles = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public String getName() {
        return email;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    public boolean hasRole(String roleName) {
        if (roles != null)
        {
            for(Role role : roles)
            {
                if(role.getName().equals(roleName))
                {
                    return true;
                }
            }
        }

        return false;
    }
}

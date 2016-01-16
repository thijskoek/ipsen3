package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.ipsen3.View;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty
    @JsonView(View.Protected.class)
    @Column(name = "password")
    private String password;

    @JsonView(View.Private.class)
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    @OrderBy(clause = "id ASC")
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_debiteur",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "debiteur_id"))
    private Debiteur debiteur;

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
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
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    @JsonIgnore
    public void hashPassword() {
        password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @JsonIgnore
    public String getName() {
        return email;
    }

    @JsonProperty
    public Set<Role> getRoles() {
        return roles;
    }

    @JsonProperty
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public boolean hasRole(String roleName) {
        if (roles != null) {
            for(Role role : getRoles()) {
                if(role.getName().equals(roleName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @JsonProperty
    public Debiteur getDebiteur() {
        return debiteur;
    }

    @JsonProperty
    public void setDebiteur(Debiteur debiteur) {
        this.debiteur = debiteur;
    }
}

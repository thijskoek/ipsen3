package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Thijs Koek on 1/20/2016.
 */
@Entity
@Table(name = "actie")

public class Actie {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "naam", nullable = false, length = 255)
    @Length(max = 255)
    private String naam;

    @Column(name = "banner", nullable = false, length = 255)
    @Length(max = 255)
    private String Banner;

    private List<Wijn> wijnLijst;

    @Column(name = "actief", nullable = false, length = 255)
    @Length(max = 255)
    private boolean actief;
    @Column(name = "date", nullable = false, length = 11)
    private Date startdatum;
    @Column(name = "date", nullable = false, length = 11)
    private Date einddatum;

    @JsonProperty
    public long getId() {
        return id;
    }
}

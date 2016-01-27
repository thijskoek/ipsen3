package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "naam", nullable = false, length = 255)
    private String naam;

    @Column(name = "banner_text", length = 255)
    private String Banner;

    @Column(name = "actief")
    private boolean actief;

    @Column(name = "startdatum")
    private DateTime startdatum;

    @Column(name = "einddatum")
    private DateTime einddatum;

    @OneToMany
    @JoinTable(name = "product_to_actie",
        joinColumns = @JoinColumn(name = "actie_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Wijn> wijnen;

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getNaam() {
        return naam;
    }

    @JsonProperty
    public void setNaam(String naam) {
        this.naam = naam;
    }

    @JsonProperty
    public String getBanner() {
        return Banner;
    }

    @JsonProperty
    public void setBanner(String banner) {
        Banner = banner;
    }

    @JsonProperty
    public boolean isActief() {
        return actief;
    }

    @JsonProperty
    public void setActief(boolean actief) {
        this.actief = actief;
    }

    @JsonProperty
    public DateTime getStartdatum() {
        return startdatum;
    }

    @JsonProperty
    public void setStartdatum(DateTime startdatum) {
        this.startdatum = startdatum;
    }

    @JsonProperty
    public DateTime getEinddatum() {
        return einddatum;
    }

    @JsonProperty
    public void setEinddatum(DateTime einddatum) {
        this.einddatum = einddatum;
    }

    @JsonProperty
    public List<Wijn> getWijnen() {
        return wijnen;
    }

    @JsonProperty
    public void setWijnen(List<Wijn> wijnen) {
        this.wijnen = wijnen;
    }
}

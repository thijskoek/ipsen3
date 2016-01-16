package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author Daan
 */
@Entity
@Table(name = "debiteur")
public class Debiteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "aanhef")
    private String aanhef;

    @Column(name = "voornaam")
    private String voornaam;

    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;

    @Column(name = "naam")
    private String naam;

    @Column(name = "adres")
    private String adres;

    @Column(name = "woonplaats")
    private String woonplaats;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "email")
    private String email;

    @Column(name = "telefoon")
    private String telefoon;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "land_id", referencedColumnName = "id")
    private Land land;

    public Debiteur() {
        // Jackson
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
    public String getAanhef() {
        return aanhef;
    }

    @JsonProperty
    public void setAanhef(String aanhef) {
        this.aanhef = aanhef;
    }

    @JsonProperty
    public String getVoornaam() {
        return voornaam;
    }

    @JsonProperty
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    @JsonProperty
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    @JsonProperty
    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
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
    public String getAdres() {
        return adres;
    }

    @JsonProperty
    public void setAdres(String adres) {
        this.adres = adres;
    }

    @JsonProperty
    public String getWoonplaats() {
        return woonplaats;
    }

    @JsonProperty
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    @JsonProperty
    public String getPostcode() {
        return postcode;
    }

    @JsonProperty
    public void setPostcode(String postcode) {
        this.postcode = postcode;
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
    public String getTelefoon() {
        return telefoon;
    }

    @JsonProperty
    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    @JsonProperty
    public Land getLand() {
        return land;
    }

    @JsonProperty
    public void setLand(Land land) {
        this.land = land;
    }
}

package nl.hsleiden.ipsen3.core;

import javax.persistence.*;

/**
 * Created by Brandon on 31-Jan-16.
 */
@Entity
@Table(name = "organisatie")
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "bedrijfsnaam")
    private String bedrijfsnaam;
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
    @Column(name = "kvk")
    private String kvk;
    @Column(name = "btw_nummer")
    private String btwnummer;
    @Column(name = "iban")
    private String iban;
    @Column(name = "bic")
    private String bic;


    public Company() {

    }

    public Company(long id, String bedrijfsnaam, String adres, String woonplaats, String postcode, String email, String telefoon, String kvk, String btwnummer, String iban, String bic) {
        this.id = id;
        this.bedrijfsnaam = bedrijfsnaam;
        this.adres = adres;
        this.woonplaats = woonplaats;
        this.postcode = postcode;
        this.email = email;
        this.telefoon = telefoon;
        this.kvk = kvk;
        this.btwnummer = btwnummer;
        this.iban = iban;
        this.bic = bic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getKvk() {
        return kvk;
    }

    public void setKvk(String kvk) {
        this.kvk = kvk;
    }

    public String getBtwnummer() {
        return btwnummer;
    }

    public void setBtwnummer(String btwnummer) {
        this.btwnummer = btwnummer;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }
}

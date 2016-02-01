package nl.hsleiden.ipsen3.core;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Roy on 27-1-2016.
 */

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private int productnummer;
    private String naam;
    private int jaar;
    private double prijs;
    private String type;
    private int land_id;
    private int rang;

    public Product(long id, int productnummer, String naam, int jaar, double prijs, String type, int land_id, int rang) {
        this.id = id;
        this.productnummer = productnummer;
        this.naam = naam;
        this.jaar = jaar;
        this.prijs = prijs;
        this.type = type;
        this.land_id = land_id;
        this.rang = rang;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProductnummer() {
        return productnummer;
    }

    public void setProductnummer(int productnummer) {
        this.productnummer = productnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLand_id() {
        return land_id;
    }

    public void setLand_id(int land_id) {
        this.land_id = land_id;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }
}
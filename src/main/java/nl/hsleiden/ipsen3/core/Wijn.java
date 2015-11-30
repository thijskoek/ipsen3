package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Created by Daan on 30-Nov-15.
 */
public class Wijn {

    private long id;

    @Length(max = 11)
    private long productnummer;

    @Length(max = 255)
    private String naam;

    @Length(max = 11)
    private int jaar;

    @Length(max = 11)
    private double prijs;

    @Length(max = 255)
    private String type;

    private int landId;

    private int rang;

    public Wijn() {
    }

    public Wijn(long id, long productnummer, String naam, int jaar, double prijs, String type, int landId, int rang) {
        this.id = id;
        this.productnummer = productnummer;
        this.naam = naam;
        this.jaar = jaar;
        this.prijs = prijs;
        this.type = type;
        this.landId = landId;
        this.rang = rang;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getNaam() {
        return naam;
    }

    @JsonProperty
    public long getProductnummer() {
        return productnummer;
    }

    @JsonProperty
    public int getJaar() {
        return jaar;
    }

    @JsonProperty
    public double getPrijs() {
        return prijs;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonProperty
    public int getLandId() {
        return landId;
    }

    @JsonProperty
    public int getRang() {
        return rang;
    }
}

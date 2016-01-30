package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Daan on 30-Nov-15.
 */
@Entity
@Table(name = "product")
public class Wijn {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "productnummer", nullable = false, length = 11)
    private long productnummer;

    @Column(name = "naam", nullable = false, length = 255)
    @Length(max = 255)
    private String naam;

    @Column(name = "jaar", nullable = false, length = 11)
    private int jaar;

    @Column(name = "prijs", nullable = false, length = 11)
    private double prijs;

    @Column(name = "type", nullable = false, length = 255)
    @Length(max = 255)
    private String type;

    @ManyToOne
    @JoinColumn(name = "land_id")
    private Land land;

    @Column(name = "rang", nullable = true, length = 11)
    private Integer rang;

    @Transient
    private DecimalFormat df = new DecimalFormat("#.00");

    public Wijn() {
    }

    public Wijn(long id, long productnummer, String naam, int jaar, double prijs, String type, Integer rang) {
        this.id = id;
        this.productnummer = productnummer;
        this.naam = naam;
        this.jaar = jaar;
        this.prijs = prijs;
        this.type = type;
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
        return Double.parseDouble(df.format(prijs));
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonProperty
    public Land getLand() {
        return land;
    }

    @JsonProperty
    public Integer getRang() {
        return rang;
    }

}

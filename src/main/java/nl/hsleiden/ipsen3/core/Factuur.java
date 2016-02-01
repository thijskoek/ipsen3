package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 21-Jan-16.
 */
@Entity
@Table(name = "factuur")
public class Factuur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "debiteur_id", referencedColumnName = "id")
    private Debiteur debiteur;

    @Column(name = "factuurnummer")
    private int factuurnummer;

    @Column(name = "factuurdatum")
    private DateTime factuurdatum;
    @Column(name = "vervaldatum")
    private DateTime vervaldatum;

    @Column(name = "status")
    private String status;

    @Column(name = "opmerking")
    private String opmerking;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "factuur")
    private List<Factuurregel> factuurregels = new ArrayList<Factuurregel>();

    @Transient
    DecimalFormat df = new DecimalFormat("#.00");

    public List<Factuurregel> getFactuurregels() {
        return factuurregels;
    }

    public void setFactuurregels(List<Factuurregel> factuurregels) {
        this.factuurregels = factuurregels;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Debiteur getDebiteur() {
        return debiteur;
    }

    public void setDebiteur(Debiteur debiteur) {
        this.debiteur = debiteur;
    }

    public int getFactuurnummer() {
        return factuurnummer;
    }

    public void setFactuurnummer(int factuurnummer) {
        this.factuurnummer = factuurnummer;
    }

    public DateTime getFactuurdatum() {
        return factuurdatum;
    }

    public void setFactuurdatum(DateTime factuurdatum) {
        this.factuurdatum = factuurdatum;
    }

    public DateTime getVervaldatum() {
        return vervaldatum;
    }

    public void setVervaldatum(DateTime vervaldatum) {
        this.vervaldatum = vervaldatum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking)   {
        this.opmerking = opmerking;
    }

    public void addFactuurregel(Factuurregel factuurregel) {
        this.factuurregels.add(factuurregel);
    }


    @JsonIgnore
    public double getTotaal() {
        double total = 0.00;
        for (Factuurregel regel: factuurregels) {
            total += (regel.getAantal()*regel.getWijn().getPrijs());
        }
        return Double.parseDouble(df.format(total));
    }

    @JsonIgnore
    public String getTotaalString() {
        return df.format(getTotaal());
    }

    @JsonIgnore
    public String getSubTotaalString() {
        return df.format((getTotaal() - getBTW()));
    }

    @JsonIgnore
    public double getBTW() {
        return Double.parseDouble(df.format(((getTotaal() / 121) * 21)));
    }

    @JsonIgnore
    public String getBTWString() {
        return df.format(((getTotaal() / 121) * 21));
    }
}


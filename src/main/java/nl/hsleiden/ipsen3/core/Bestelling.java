package nl.hsleiden.ipsen3.core;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roy on 27-1-2016.
 */
@Entity
@Table(name = "factuur")
public class Bestelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long debiteur_id;
    private long factuurnummer;
    private Date vervaldatum;
    private String status;
    private String opmerking;
    private String pdfpath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDebiteur_id() {
        return debiteur_id;
    }

    public void setDebiteur_id(long debiteur_id) {
        this.debiteur_id = debiteur_id;
    }

    public long getFactuurnummer() {
        return factuurnummer;
    }

    public void setFactuurnummer(long factuurnummer) {
        this.factuurnummer = factuurnummer;
    }

    public Date getVervaldatum() {
        return vervaldatum;
    }

    public void setVervaldatum(Date vervaldatum) {
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

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public String getPdfpath() {
        return pdfpath;
    }

    public void setPdfpath(String pdfpath) {
        this.pdfpath = pdfpath;
    }
}

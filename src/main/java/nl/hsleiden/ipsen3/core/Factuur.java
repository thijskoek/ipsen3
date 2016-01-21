package nl.hsleiden.ipsen3.core;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Brandon on 21-Jan-16.
 */
@Entity
@Table(name = "factuur")
public class Factuur {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "debiteur_id", referencedColumnName = "id")
    private Debiteur debiteur;

    @Column(name = "factuurnummer")
    private int factuurnummer;

    private DateTime factuurdatum;
    private DateTime vervaldatum;

    @Column(name = "status")
    private String status;

    @Column(name = "opmerking")
    private String opmerking;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tbl_order",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "factuur_id"))
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }
}

package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.DecimalFormat;

/**
 * @author Daan
 */
@Entity
@Table(name = "tbl_order")
public class Factuurregel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "aantal")
    private int aantal;

    @OneToOne(cascade = {CascadeType.ALL})
    private Factuur factuur;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    private Wijn wijn;

    public Factuurregel() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAantal() {
        return aantal;
    }

    public Wijn getWijn() {
        return wijn;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public void setWijn(Wijn wijn) {
        this.wijn = wijn;
    }

    @JsonIgnore
    public Factuur getFactuur() {
        return factuur;
    }

    @JsonIgnore
    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }

    @JsonIgnore
    public double getTotaal() {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format((getAantal() * getWijn().getPrijs())));
    }

}



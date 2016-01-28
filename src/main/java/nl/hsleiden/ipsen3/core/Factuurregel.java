package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "aantal")
    private int aantal;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "factuur_id")
    private Factuur factuur;

    @OneToOne(cascade = {CascadeType.ALL})
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

    public Factuur getFactuur() {
        return factuur;
    }

    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }

    @JsonIgnore
    public double getTotaal() {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format((getAantal() * getWijn().getPrijs())));
    }
}

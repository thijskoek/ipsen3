package nl.hsleiden.ipsen3.core;

import javax.persistence.*;

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

    @Column(name = "aantal", nullable = false)
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
}

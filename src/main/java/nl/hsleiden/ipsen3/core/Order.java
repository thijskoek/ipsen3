package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Brandon on 16-Jan-16.
 */
@Entity
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "aantal", nullable = false, length = 11)
    @Length(max = 11)
    private int aantal;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Wijn wijn;

    public Order() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public Wijn getWijn() {
        return wijn;
    }

    public void setWijn(Wijn wijn) {
        this.wijn = wijn;
    }
}

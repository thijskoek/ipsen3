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
public class Order implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Id
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "jaar", nullable = false, length = 11)
    @Length(max = 11)
    private int aantal;

    public Order() {

    }

    public Order(long id, int product_id, int aantal) {
        this.id = id;
        this.product_id = product_id;
        this.aantal = aantal;

    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public int getProduct_id() {
        return product_id;
    }

    @JsonProperty
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @JsonProperty
    public int getAantal() {
        return aantal;
    }

    @JsonProperty
    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product_id=" + product_id +
                ", aantal=" + aantal +
                '}';
    }
}

package nl.hsleiden.ipsen3.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Brandon on 16-Jan-16.
 */
public class Order {

    private int product_id;
    private int aantal;


    public Order(int product_id, int aantal) {
        this.product_id = product_id;
        this.aantal = aantal;
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

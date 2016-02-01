package nl.hsleiden.ipsen3.core.helper;

import nl.hsleiden.ipsen3.core.Wijn;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.ipsen3.View;

/**
 * @author Daan
 */
public class OrderRegel {

    @JsonView(View.Public.class)
    private int aantal;

    @JsonView(View.Public.class)
    private Wijn wijn;

    public OrderRegel() {}

    public OrderRegel(int aantal, Wijn wijn) {
        this.aantal = aantal;
        this.wijn = wijn;
    }

    @JsonProperty
    public int getAantal() {
        return aantal;
    }
    @JsonProperty
    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
    @JsonProperty
    public Wijn getWijn() {
        return wijn;
    }
    @JsonProperty
    public void setWijn(Wijn wijn) {
        this.wijn = wijn;
    }
}

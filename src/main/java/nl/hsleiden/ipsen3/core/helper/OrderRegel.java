package nl.hsleiden.ipsen3.core.helper;

import nl.hsleiden.ipsen3.core.Wijn;

/**
 * @author Daan
 */
public class OrderRegel {

    private int aantal;
    private Wijn wijn;

    public OrderRegel() {}

    public OrderRegel(int aantal, Wijn wijn) {
        this.aantal = aantal;
        this.wijn = wijn;
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

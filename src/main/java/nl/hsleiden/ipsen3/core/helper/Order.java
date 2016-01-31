package nl.hsleiden.ipsen3.core.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hsleiden.ipsen3.core.Debiteur;

import java.util.List;

/**
 * Created by Brandon on 16-Jan-16.
 */
public class Order {

    private Debiteur debiteur;
    private List<OrderRegel> regels;

    public Order() {}

    public Order(Debiteur debiteur, List<OrderRegel> regels) {
        this.debiteur = debiteur;
        this.regels = regels;
    }

    @JsonProperty
    public Debiteur getDebiteur() {
        return debiteur;
    }

    @JsonProperty
    public void setDebiteur(Debiteur debiteur) {
        this.debiteur = debiteur;
    }

    public List<OrderRegel> getRegels() {
        return regels;
    }

    public void setRegels(List<OrderRegel> regels) {
        this.regels = regels;
    }
}

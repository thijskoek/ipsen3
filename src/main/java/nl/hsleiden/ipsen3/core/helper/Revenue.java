package nl.hsleiden.ipsen3.core.helper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hsleiden.ipsen3.core.Factuur;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Daan Rosbergen
 */
public class Revenue {

    @JsonIgnore
    private List<Factuur> facturen;
    @JsonIgnore
    private Map<String, Double> revenue = new LinkedHashMap<String, Double>();
    @JsonIgnore
    private String[] months = {
        "Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus",
        "September", "Oktober", "November", "December"
    };

    public Revenue(List<Factuur> facturen) {
        this.facturen = facturen;
        this.buildRevenue();
    }

    private void buildRevenue() {
        for (int i = 0; i < months.length; i++) {
            Double total = 0.00;
            for (Factuur factuur: facturen) {
                if ((factuur.getFactuurdatum().getMonthOfYear()-1) == i) {
                    total += factuur.getTotaal();
                }
            }
            revenue.put(months[i], total);
        }
    }

    @JsonProperty
    public Map<String, Double> getRevenue() {
        return revenue;
    }

}

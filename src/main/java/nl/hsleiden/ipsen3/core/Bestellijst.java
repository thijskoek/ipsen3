package nl.hsleiden.ipsen3.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Victor on 11-1-2016.
 */

@Entity
@Table(name = "bestellijst")
public class Bestellijst {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "date", nullable = false, length = 11)
    private DateTime date;

    @Column(name = "naam", nullable = false, length = 255)
    @Length(max = 255)
    private String naam;

    public Bestellijst(){

    }

    public Bestellijst(long id, DateTime date, String naam){
        this.id = id;
        this.date = date;
        this.naam = naam;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public DateTime getDate() {
        return date;
    }

    @JsonProperty
    public String getName(){
        return naam;
    }
}


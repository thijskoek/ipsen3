package nl.hsleiden.ipsen3.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

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
    private long date;

    @Column(name = "naam", nullable = false, length = 255)
    @Length(max = 255)
    private long naam;

    public Bestellijst(){

    }

    public Bestellijst(long id, long date, long naam){
        this.id = id;
        this.date = date;
        this.naam = naam;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public long getDate() {
        return date;
    }

    @JsonProperty
    public long getName(){
        return naam;
    }
}


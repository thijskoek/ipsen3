package nl.hsleiden.ipsen3.core;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roy on 21-1-2016.
 * Represents the key used for password recovery.
 */
@Entity
@Table(name = "sleutels")
public class Sleutel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private long user_id;
    private String sleutel;
    @Column(updatable = false, insertable = false)
    private Date created_at;
    private int used;


    public long getId() {
        return this.id;
    }

    public String getSleutel() {
        return this.sleutel;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public DateTime getCreated_at() {
        return new DateTime(this.created_at);
    }

    public int getUsed() {
        return this.used;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setSleutel(String sleutel) {
        this.sleutel = sleutel;
    }

    public Sleutel used() {
        this.used = 1;
        return this;
    }
}

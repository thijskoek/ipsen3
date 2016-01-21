package nl.hsleiden.ipsen3.core;

import javax.persistence.*;
import java.sql.Date;

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

    public long getId() {
        return this.id;
    }

    public String getSleutel() {
        return this.sleutel;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setSleutel(String sleutel) {
        this.sleutel = sleutel;
    }
}

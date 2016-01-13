package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Bestellijst;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Victor on 11-1-2016.
 */
public class BestellijstDAO extends AbstractDAO<Bestellijst>{

    public BestellijstDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Bestellijst findById(Long id) {
        return get(id);
    }

    public long create(Bestellijst bestellijst){
        return persist(bestellijst).getId();
    }

    public List<Bestellijst> findAll(){
        return currentSession().createCriteria(Bestellijst.class).list();
    }
}

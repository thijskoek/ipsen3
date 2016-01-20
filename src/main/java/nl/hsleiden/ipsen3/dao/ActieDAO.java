package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Actie;
import nl.hsleiden.ipsen3.core.Bestellijst;
import nl.hsleiden.ipsen3.core.Wijn;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Thijs Koek on 1/20/2016.
 */
public class ActieDAO extends AbstractDAO<Actie> {
    public ActieDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Actie findById(Long id) {
        return get(id);
    }
    public long create(Actie actie){
        return persist(actie).getId();
    }
        public List<Actie> findAll() { return currentSession().createCriteria(Actie.class).list(); }

}

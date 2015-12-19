package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Wijn;
import org.hibernate.SessionFactory;

import java.util.List;

public class WijnDAO extends AbstractDAO<Wijn> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public WijnDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Wijn findById(Long id) {
        return get(id);
    }

    public long create(Wijn wijn) {
        return persist(wijn).getId();
    }

    public List findAll() {
        return currentSession().createCriteria(Wijn.class).list();
    }
}

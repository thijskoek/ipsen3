package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Sleutel;
import org.hibernate.SessionFactory;

/**
 * Created by Roy on 21-1-2016.
 */
public class SleutelDAO extends AbstractDAO<Sleutel> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public SleutelDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Long create(Sleutel sleutel) {
        return Long.valueOf(persist(sleutel).getId());
    }

}

package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Bestelling;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Roy on 27-1-2016.
 */
public class BestellingDAO extends AbstractDAO<Bestelling> {

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public BestellingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Bestelling> findByUser(long userId) {
        List<Bestelling> bestellingen = currentSession().createQuery(
                "SELECT id FROM factuur"
                ).list();
        return bestellingen;
    }
}

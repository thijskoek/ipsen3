package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.App;
import nl.hsleiden.ipsen3.core.*;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.QueryImpl;
import org.hibernate.internal.SQLQueryImpl;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daan
 */
public class FactuurDAO extends AbstractDAO<Factuur> {
    private final Logger logger = LoggerFactory.getLogger(App.class);
    private final SessionFactory sessionFactory;

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public FactuurDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public int getLastFactuurNummer() {
        return (Integer) criteria().setProjection(Projections.max("factuurnummer")).uniqueResult();
    }

    public long create(Factuur factuur) {
        return persist(factuur).getId();
    }

    /**
     * Creates a invoice for a given order.
     *
     * @param order
     * @return
     */
    public long create(Order order)
    {
        Factuur factuur = new Factuur();
        factuur.setFactuurnummer(getLastFactuurNummer()+1);
        factuur.setDebiteur(order.getDebiteur());
        factuur.setFactuurdatum(new DateTime());
        factuur.setVervaldatum(new DateTime().plusDays(14));
        factuur.setStatus("concept");

        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            try {
                session.evict(factuur);
                session.saveOrUpdate(factuur);
                transaction.commit();
                session.flush();
            } catch (Exception e) {
                transaction.rollback();
                logger.error(e.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return factuur.getId();
    }

    public Factuur findById(long id) {
        return get(id);
    }
}

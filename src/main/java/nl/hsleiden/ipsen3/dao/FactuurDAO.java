package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.App;
import nl.hsleiden.ipsen3.core.*;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
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
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public FactuurDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
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
        for (OrderRegel orderRegel: order.getRegels()) {
            Factuurregel factuurregel = new Factuurregel();
            factuurregel.setAantal(orderRegel.getAantal());
            factuurregel.setWijn(orderRegel.getWijn());
            factuurregel.setFactuur(factuur);
            factuur.addFactuurregel(factuurregel);
        }
        factuur.setFactuurdatum(new DateTime());
        factuur.setVervaldatum(new DateTime().plusDays(14));
        factuur.setStatus("concept");
        return create(factuur);
    }
}

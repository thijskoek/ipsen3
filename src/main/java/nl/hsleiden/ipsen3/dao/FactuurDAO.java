package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.App;
import nl.hsleiden.ipsen3.core.Factuur;
import nl.hsleiden.ipsen3.core.Factuurregel;
import nl.hsleiden.ipsen3.core.helper.Order;
import nl.hsleiden.ipsen3.core.helper.OrderRegel;
import nl.hsleiden.ipsen3.core.helper.Revenue;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

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

        for (OrderRegel orderRegel: order.getRegels()) {
            Factuurregel factuurregel = new Factuurregel();
            factuurregel.setAantal(orderRegel.getAantal());
            factuurregel.setWijn(orderRegel.getWijn());
            factuurregel.setFactuur(factuur);
            factuur.addFactuurregel(factuurregel);
        }
        return persist(factuur).getId();
    }

    public Factuur findById(long id) {
        return get(id);
    }

    public List<Factuur> findAll(String status, DateTime year) {
        Criteria criteria = currentSession().createCriteria(Factuur.class);
        if (status != null) {
            criteria.add(Restrictions.eq("status", status));
        }
        if (year != null) {
            criteria.add(buildYearQuery(year));
        }
        List<Factuur> facturen = criteria.list();
        for (Factuur factuur : facturen) {
            initialize(factuur.getFactuurregels());
        }
        return facturen;
    }

    public Map<String, Double> getRevenue(DateTime year) {
        double revenue = 0.00;
        Criteria criteria = currentSession().createCriteria(Factuur.class);
        if (year != null) {
            criteria.add(buildYearQuery(year));
        }

        List<Factuur> facturen = criteria.list();
        for (Factuur factuur: facturen) {
            initialize(factuur.getFactuurregels());
        }

        return new Revenue(facturen).getRevenue();
    }

    private Conjunction buildYearQuery(DateTime year) {
        Conjunction and = Restrictions.conjunction();
        DateTime minJaar = year;
        DateTime maxJaar = new DateTime(year).plusYears(1);

        and.add(Restrictions.ge("factuurdatum", minJaar));
        and.add(Restrictions.lt("factuurdatum", maxJaar));
        return and;
    }
}

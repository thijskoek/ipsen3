package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.App;
import nl.hsleiden.ipsen3.core.Factuur;
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

    public List<Factuur> findByDebiteur(long debiteurId) {
        Criteria criteria = currentSession().createCriteria(Factuur.class);
        return criteria.list();
    }

    public int getLastFactuurNummer() {
        return (Integer) criteria().setProjection(Projections.max("factuurnummer")).uniqueResult();
    }

    public long create(Factuur factuur) {
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



    private Conjunction buildYearQuery(DateTime year) {
        Conjunction and = Restrictions.conjunction();
        DateTime minJaar = year;
        DateTime maxJaar = new DateTime(year).plusYears(1);

        and.add(Restrictions.ge("factuurdatum", minJaar));
        and.add(Restrictions.lt("factuurdatum", maxJaar));
        return and;
    }
}
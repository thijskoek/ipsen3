package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Sleutel;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.joda.time.Instant;
import org.joda.time.Interval;

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

    public void update(Sleutel sleutel) {
        currentSession().saveOrUpdate(sleutel);
    }

    public Long create(Sleutel sleutel) {
        return Long.valueOf(persist(sleutel).getId());
    }

    public Sleutel findByKey(String sleutel) {
        Criteria criteria = currentSession().createCriteria(Sleutel.class);
        criteria.add(Restrictions.like("sleutel", sleutel));
        return (Sleutel) criteria.uniqueResult();
    }

    public boolean exists(String sleutel) {
        Criteria criteria = currentSession().createCriteria(Sleutel.class);
        criteria.add(Restrictions.like("sleutel", sleutel));
        Sleutel s = (Sleutel) criteria.uniqueResult();
        try {
            if(s.getSleutel() != null && s.getUsed() == 0) {
                Interval interval = new Interval(s.getCreated_at(), new Instant());
                if(interval.toDurationMillis() < 900000) { //IF interval is less than 15 minutes.
                    return true;
                } else {
                    return false;
                }
            }
        } catch(NullPointerException ex) {
            return false;
        }
        return false;
    }

}

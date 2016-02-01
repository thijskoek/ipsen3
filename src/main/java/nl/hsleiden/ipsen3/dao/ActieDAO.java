package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Actie;
import nl.hsleiden.ipsen3.core.Wijn;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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

    public Actie findAll() {
        Actie actie = (Actie) currentSession().createCriteria(Actie.class)
                .add(Restrictions.eq("actief", true)).uniqueResult();
        initialize(actie.getWijnen());
        for (Wijn wijn: actie.getWijnen()) {
            initialize(wijn.getImages());
        }
        return actie;
    }

}

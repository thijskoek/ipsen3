package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    private final SessionFactory sessionFactory;

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public User findById(long id) {
        return get(id);
    }

    public long create(User user) {
        return persist(user).getId();
    }

    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        try {
            return (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
        } finally {
            session.clear();
        }
    }

    public List findAll() {
        return currentSession().createCriteria(User.class).list();
//        return criteria().list();
    }

    public void add(User user) {

    }

    public void update(User authenticator, int id, User user) {

    }

    public void delete(int id) {

    }
}

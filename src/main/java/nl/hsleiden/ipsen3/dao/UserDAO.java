package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class UserDAO extends AbstractDAO<User> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findById(int id) {
        return get(id);
    }

    public long create(User user) {
        return persist(user).getId();
    }

    public User getByEmail(String email) {
        return (User) currentSession().createCriteria(User.class).add(Restrictions.
            eq("emailadres", email)).uniqueResult();
    }
}

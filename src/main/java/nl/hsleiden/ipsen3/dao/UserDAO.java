package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author Daan Rosbergen
 */
public class UserDAO extends AbstractDAO<User> {
    private final SessionFactory sessionFactory;

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public UserDAO(SessionFactory sessionFactory)
    {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * Find a user by given id.
     *
     * @param id
     * @return
     */
    public User findById(long id)
    {
        initialize(User.class);
        return get(id);
    }

    /**
     * Saves a given user to the database.
     *
     * @param user
     * @return
     */
    public long create(User user)
    {
        user.hashPassword();
        return persist(user).getId();
    }

    /**
     * Finds a user by email.
     *
     * @param email
     * @return
     */
    public User getByEmail(String email)
    {
        Session session = sessionFactory.openSession();
        try {
            User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email))
                .uniqueResult();
            Hibernate.initialize(user.getRoles());
            return user;
        } finally {
            session.clear();
        }
    }



    /**
     * Retrieve all users.
     *
     * @return
     */
    public List findAll()
    {
        return criteria().list();
    }

}

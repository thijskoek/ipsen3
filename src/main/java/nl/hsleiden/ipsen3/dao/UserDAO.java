package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Land;
import nl.hsleiden.ipsen3.core.Role;
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
        Land land = (Land) currentSession()
                .createCriteria(Land.class)
                .add(Restrictions.eq("name", user.getDebiteur().getLand().getName()))
                .uniqueResult();
        Role role = (Role) currentSession()
                .createCriteria(Role.class)
                .add(Restrictions.eq("name", "klant"))
                .uniqueResult();
        user.getDebiteur().setLand(land);
        user.getRoles().add(role);
        user.hashPassword();
        return persist(user).getId();
    }

    public void update(User user) {
        currentSession().saveOrUpdate(user);
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
            if (user != null) {
                Hibernate.initialize(user.getRoles());
            }
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

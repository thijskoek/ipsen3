package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Order;
import org.hibernate.SessionFactory;

/**
 * Created by Brandon on 16-Jan-16.
 */
public class OrderDAO extends AbstractDAO<Order> {

    private final SessionFactory sessionFactory;

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public OrderDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }


    /**
     * Find a user by given id.
     *
     * @param id
     * @return
     */
    public Order findById(long id)
    {
        initialize(Order.class);
        return get(id);
    }

    /**
     * Saves a given user to the database.
     *
     * @param order
     * @return
     */
    public long create(Order order)
    {
        return persist(order).getId();
    }
}

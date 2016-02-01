package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Product;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 27-1-2016.
 */
public class BestellingDAO extends AbstractDAO<Product> {

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public BestellingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Product> findByUser(long userId) {
        List<Product> producten = new ArrayList<>();
        producten.add(new Product(1, 1, "Bordeaux", 1992, 19.50, "Rood", 1, 7));
        producten.add(new Product(2, 2, "Côtes du Rhône", 1772, 30.00, "Rood", 1, 7));
        producten.add(new Product(3, 3, "Bourgogne", 2002, 17.99, "Rood", 1, 7));
        producten.add(new Product(4, 4, "Cabernet sauvignon", 1995, 130.17, "Wit", 1, 7));
        producten.add(new Product(5, 6, "Chanti", 2005, 9.50, "Wit", 1, 7));
        return producten;
    }
}

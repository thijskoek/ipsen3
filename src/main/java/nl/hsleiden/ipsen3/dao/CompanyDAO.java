package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Company;
import org.hibernate.SessionFactory;

/**
 * Created by Brandon on 31-Jan-16.
 */
public class CompanyDAO extends AbstractDAO<Company> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public CompanyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Company findById(Long id) { return get(id); }

    public long create(Company company) { return persist(company).getId(); }
}

package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Company;
import org.hibernate.SessionFactory;

import java.util.List;

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

    public List<Company> findAll() { return currentSession().createCriteria(Company.class).list(); }

    //Merge the old object with the new attributes from the client.
    public Long update(Company current) {
        Company old = this.findById((long) 2);
        old.setBedrijfsnaam(current.getBedrijfsnaam());
        old.setAdres(current.getAdres());
        old.setWoonplaats(current.getWoonplaats());
        old.setPostcode(current.getPostcode());
        old.setAdres(current.getAdres());
        old.setWoonplaats(current.getWoonplaats());
        old.setPostcode(current.getPostcode());
        old.setEmail(current.getEmail());
        old.setTelefoon(current.getTelefoon());
        old.setKvk(current.getKvk());
        old.setBtwnummer(current.getBtwnummer());
        old.setIban(current.getIban());
        old.setBic(current.getBic());

        currentSession().saveOrUpdate(old);
        return current.getId();
    }
}

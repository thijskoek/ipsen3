package nl.hsleiden.ipsen3.dao;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.ipsen3.core.Gebruiker;
import nl.hsleiden.ipsen3.core.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;

/**
 * Created by Roy on 14-1-2016.
 */
public class GebruikerDAO extends AbstractDAO<Gebruiker> {

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public GebruikerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Gebruiker findById(long id) {
        return get(id);
    }

    //Find debiteur by user data.
    public Gebruiker findByUserId(int id) {
        Criteria criteria = currentSession().createCriteria(Gebruiker.class);
        criteria.add(Restrictions.like("user_id", id));
        return (Gebruiker) criteria.uniqueResult();
    }

    public Gebruiker findByMail(String mail) {
        Criteria criteria = currentSession().createCriteria(Gebruiker.class);
        criteria.add(Restrictions.like("email", mail));
        return (Gebruiker) criteria.uniqueResult();
    }

    //Merge the old object with the new attributes from the client.
    public Long update(Gebruiker current, User user) {
        Gebruiker old = this.findByMail(user.getEmail());
        old.setAanhef(current.getAanhef());
        old.setVoornaam(current.getVoornaam());
        old.setTussenvoegsel(current.getTussenvoegsel());
        old.setNaam(current.getNaam());
        old.setAdres(current.getAdres());
        old.setWoonplaats(current.getWoonplaats());
        old.setPostcode(current.getPostcode());
        old.setEmail(current.getEmail());
        old.setTelefoon(current.getTelefoon());
        user.setEmail(current.getEmail());
        currentSession().saveOrUpdate(user);
        return this.create(old);    
    }

    public Long create(Gebruiker gebruiker) {
        return Long.valueOf(persist(gebruiker).getId());
    }
}

package nl.hsleiden.ipsen3.core;

import nl.hsleiden.ipsen3.dao.UserDAO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Daan
 */
public class UserTest {

    @Test
    public void itShouldHaveAnEmailadress() {
        final User user = new User();
        user.setEmail("d.rosbergen@gmail.com");
        assertEquals("d.rosbergen@gmail.com", user.getEmail());
    }

}

package nl.hsleiden.ipsen3.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Daan
 */
public class UserTest {

    @Test
    public void theUserHasAEmailaddres() {
        User user = new User();
        user.setEmail("d.rosbergen@gmail.com");
        assertEquals("d.rosbergen@gmail.com", user.getEmail());
    }

}

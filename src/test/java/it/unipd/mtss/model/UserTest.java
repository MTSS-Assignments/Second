////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class UserTest {
    
    private User user;
    
    @Before
    public void inizializeUser() {
        user = new User("Mario", "Rossi", LocalDate.of(1980, 1, 1));
    }
    
    @Test
    public void testGetName() {
        String userName = user.getName();
        assertEquals("Mario", userName);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullName() {
        User user = new User(null, "null", LocalDate.of(1980, 1, 1));
        user.getName();
    }

    @Test
    public void testGetSurame() {
        String userSurname = user.getSurname();
        assertEquals("Rossi", userSurname);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testNullSurname() {
        User user = new User("null", null, LocalDate.of(1980, 1, 1));
        user.getSurname();
    }

    @Test
    public void testGetDateofBirth() {
        LocalDate userDateOfBirth = user.getDate_of_birth();
        assertEquals(LocalDate.of(1980, 1, 1), userDateOfBirth);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullDateOfBirth() {
        User user = new User("null", "null", null);
        user.getDate_of_birth();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testLegalDateOfBirth() {
        User user = new User("Cod", "ing", LocalDate.of(3000, 1, 1));
    }

    @Test
    public void testGetId() {
        assertEquals(user.getId(), user.id);
    }
}

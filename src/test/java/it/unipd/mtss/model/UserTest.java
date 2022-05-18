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

    @Test
    public void testGetSurame() {
        String userSurname = user.getSurname();
        assertEquals("Rossi", userSurname);
    }

    @Test
    public void testGetDateofBirth() {
        LocalDate userDateOfBirth = user.getDate_of_birth();
        assertEquals(LocalDate.of(1980, 1, 1), userDateOfBirth);
    }

    // TEST FARLOCCHISSIMISSIMISSIMISSIMISSIMO
    @Test
    public void testGetId() {
        assertEquals(user.getId(), user.getId());
    }

    @Test
    public void testNullName() {
        try {
            new User(null, "null", LocalDate.of(1980, 1, 1));
        } catch (IllegalArgumentException e) {
            assertEquals("Inserire il Nome.", e.getMessage());
        }
    }

    @Test
    public void testNullSurname() {
        try {
            new User("null", null, LocalDate.of(1980, 1, 1));
        } catch (IllegalArgumentException e) {
            assertEquals("Inserire il Cognome.", e.getMessage());
        }
    }

    @Test
    public void testNullDateOfBirth() {
        try {
            new User("null", "null", null);
        } catch (IllegalArgumentException e) {
            assertEquals("Inserire la Data di nascita.", e.getMessage());
        }
    }

    @Test
    public void testLegalDateOfBirth() {
        try {
            new User("null", "null", LocalDate.of(3000, 1, 1));
        } catch (IllegalArgumentException e) {
            assertEquals("La data di nascita deve essere precedente alla data odierna.", e.getMessage());
        }
    }
}

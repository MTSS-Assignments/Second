////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;;

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
    public void testNullNameException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(null, "null", LocalDate.of(1980, 1, 1));
            String name = user.getName();
        });

        assertEquals("Inserire il Nome.", thrown.getMessage());
    }

    @Test
    public void testGetSurame() {
        String userSurname = user.getSurname();
        assertEquals("Rossi", userSurname);
    }

    @Test
    public void testNullSurnameException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("null", null, LocalDate.of(1980, 1, 1));
            String name = user.getSurname();
        });

        assertEquals("Inserire il Cognome.", thrown.getMessage());
    }

    @Test
    public void testGetDateofBirth() {
        LocalDate userDateOfBirth = user.getDate_of_birth();
        assertEquals(LocalDate.of(1980, 1, 1), userDateOfBirth);
    }

    @Test
    public void testTooOldDateOfBirthException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("null", "null", LocalDate.of(1871, 1, 1));
            LocalDate birth = user.getDate_of_birth();
        });

        assertEquals("La data di nascita inserita è troppo indietro nel tempo.", thrown.getMessage());
    }

    @Test
    public void testIllegalDateOfBirthException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("null", "null", LocalDate.of(3980, 1, 1));
            LocalDate birth = user.getDate_of_birth();
        });

        assertEquals("La data di nascita deve essere precedente alla data odierna.", thrown.getMessage());
    }

    
    @Test
    public void testNullDateOfBirthException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("null", "null", null);
            LocalDate birth = user.getDate_of_birth();
        });
        
        assertEquals("Inserire la Data di nascita.", thrown.getMessage());
    }
    
    @Test
    public void testGetId() {
        assertEquals(user.getId(), user.id);
    }
}
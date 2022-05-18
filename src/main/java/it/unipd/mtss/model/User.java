////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String surname;
    private LocalDate date_of_birth;

    public User(String _name, String _surname, LocalDate _date_of_birth) {
        this.id = UUID.randomUUID().toString();
        
        if(_name == null) {
            throw new IllegalArgumentException("Inserire il Nome.");
        }
        else{
            this.name = _name;
        }

        if(_surname == null) {
            throw new IllegalArgumentException("Inserire il Cognome.");
        }
        else{
            this.surname = _surname;
        }

        if(_date_of_birth == null) {
            throw new IllegalArgumentException("Inserire la Data di nascita.");
        }
        // else if(_date_of_birth < LocalDate.now().minusYears(100)){
        //     throw new IllegalArgumentException("La data di nascita inserita è troppo indietro nel tempo.");
        // }
        else if(_date_of_birth.isBefore(LocalDate.now())) {
            this.date_of_birth = _date_of_birth;
        }else {
            throw new IllegalArgumentException("La data di nascita deve essere precedente alla data odierna.");
        }
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public LocalDate getDate_of_birth() {
        return this.date_of_birth;
    }
}
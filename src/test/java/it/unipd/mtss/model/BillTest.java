////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.mtss.business.BillImpl;
import it.unipd.mtss.business.exception.BillException;

public class BillTest {


    @Test
    public void testTotalPrice() {
        List<EItem> lista = new ArrayList<EItem>();
        lista.add(new EItem(EItem.item.Processor, "processore", 100));
        lista.add(new EItem(EItem.item.Processor, "processore2", 100));
        
        BillImpl impl = new BillImpl();
        LocalDate nascita = LocalDate.of(1997,11,30);
        User user = new User("Luca", "Busacca", nascita);
        
        LocalTime lt = LocalTime.now();
        try {
            impl.getOrderPrice(lista, user, lt); 
        } catch (BillException e) {
            assertEquals("lista null", e.getMessage());
        }  
    }

    @Test
    public void testTotalPriceNullList() {
        List<EItem> lista = null;
        BillImpl impl = new BillImpl();
        LocalDate nascita = LocalDate.of(1997,11,30);
        User user = new User("Luca", "Busacca", nascita);
        
        LocalTime lt = LocalTime.now();
        try {
            impl.getOrderPrice(lista, user, lt); 
        } catch (BillException e) {
            assertEquals("lista null", e.getMessage());
        }  
    }
}

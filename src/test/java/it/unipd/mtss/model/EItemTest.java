////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class EItemTest {
    private EItem item;

    @Before
    public void initializeItem() {
        item = new EItem(EItem.item.Processor, "processore bello", 102.50);
    }

    @Test
    public void testGetItemType() {
        EItem.item itemType = item.getItemType();
        assertEquals(EItem.item.Processor, itemType);
    }

    @Test
    public void testGetItemName() {
        String itemName = item.getName();
        assertEquals("processore bello", itemName);
    }

    @Test
    public void testGetItemPrice() {
        double itemPrice = item.getPrice();
        assertEquals (102.50, itemPrice,0.0);
    }

    @Test
    public void testNegativePriceInput() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, 
        () -> {
            new EItem(EItem.item.Processor, "processore bello", -1);
        });
        
        assertEquals("Il prezzo deve avre un valore >= 0", thrown.getMessage());
    }
    
    @Test
    public void testNullItemTypeInput() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, 
        () -> {
            new EItem(null, "processore bello", 100);
        });

        assertEquals("Il campo itType non può essere null", thrown.getMessage());
    }

    @Test
    public void testNullNameInput() {
        IllegalArgumentException thrown1 = assertThrows(IllegalArgumentException.class, 
        () -> {
            new EItem(EItem.item.Processor, null , 100);
        });

        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, 
        () -> {
            new EItem(EItem.item.Processor, "", 100);
        });

        assertEquals("Il campo name non può essere vuoto", thrown1.getMessage());
        assertEquals("Il campo name non può essere vuoto", thrown2.getMessage());
    }
}

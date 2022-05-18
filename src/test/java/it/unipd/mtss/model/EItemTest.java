////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EItemTest {
    private EItem item;

    @Before
    private void initializeItem() {
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
        assertEquals(item, itemName);
    }

    @Test
    public void testGetItemPrice() {
        double itemPrice = item.getPrice();
        assertEquals(item, itemPrice);
    }

    @Test
    public void testNegativePriceInput() {
        try {
            EItem item = new EItem(EItem.item.Processor, "processore bello", -1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Il prezzo deve avre un valore >= 0", exception.getMessage());
        }
    }
    
    @Test
    public void testNullItemTypeInput() {
        try {
            EItem item = new EItem(null, "processore bello", 100);
        } catch (IllegalArgumentException exception) {
            assertEquals("Il campo itType non può essere null", exception.getMessage());
        }
    }

    @Test
    public void testNullNameInput() {
        try {
            EItem item = new EItem(EItem.item.Processor, null , 100);
        } catch (IllegalArgumentException exception) {
            assertEquals("Il campo name non può essere vuoto", exception.getMessage());
        }

        try {
            EItem item2 = new EItem(EItem.item.Processor, "", 100);
        } catch (IllegalArgumentException exception) {
            assertEquals("Il campo name non può essere vuoto", exception.getMessage());
        }
    }
}

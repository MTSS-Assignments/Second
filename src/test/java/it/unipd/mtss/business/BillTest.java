////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.ArrayList;
import java.util.List;

import it.unipd.mtss.model.EItem.item;
import it.unipd.mtss.model.EItem;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class BillTest {
    private List<EItem> order = new ArrayList<EItem>();
    private List<EItem> order1 = new ArrayList<EItem>();
    @Before
    public void inizializeOrder() {
        order.add(new EItem(item.Processor, "Processore 2", 269.99));
        order.add(new EItem(item.Processor, "Processore 1", 250.00));
        order.add(new EItem(item.Processor, "Processore 3", 299.99));
        order.add(new EItem(item.Processor, "Processore 4", 349.99));
        order.add(new EItem(item.Processor, "Processore 5", 469.99));
        order.add(new EItem(item.Mouse, "Mouse 1", 14.99));
        order.add(new EItem(item.Mouse, "Mouse 2", 19.99));
        order.add(new EItem(item.Mouse, "Mouse 3", 49.99));
        order.add(new EItem(item.Mouse, "Mouse 4", 29.99));
        order.add(new EItem(item.Mouse, "Mouse 5", 89.99));
        order.add(new EItem(item.Motherboard, "SchedaMadre 1", 149.99));
        order.add(new EItem(item.Motherboard, "SchedaMadre 2", 129.99));
        order.add(new EItem(item.Motherboard, "SchedaMadre 3", 199.99));
        order.add(new EItem(item.Motherboard, "SchedaMadre 4", 249.99));
        order.add(new EItem(item.Motherboard, "SchedaMadre 5", 289.99));
        order.add(new EItem(item.Keyboard, "Tastiera 1", 89.99));
        order.add(new EItem(item.Keyboard, "Tastiera 2", 49.99));
        order.add(new EItem(item.Keyboard, "Tastiera 3", 29.99));
        order.add(new EItem(item.Keyboard, "Tastiera 4", 99.99));
        order.add(new EItem(item.Keyboard, "Tastiera 5", 119.99));

        order1.add(new EItem(item.Keyboard, "Tastiera 5", 119.99));
    }

    @Test
    public void testScontoProcessori() {
        double sconto = BillImpl.scontoProcessori(order);
        assertEquals(125, sconto, 0.0);
    }

    @Test
    public void testScontoProcessoriFail() {
        double sconto = BillImpl.scontoProcessori(order1);
        assertEquals(0, sconto, 0.0);
    }
}

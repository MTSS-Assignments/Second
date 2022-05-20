////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.unipd.mtss.model.EItem.item;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;
import org.hamcrest.core.AnyOf;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BillTest {
    private List<EItem> order = new ArrayList<EItem>();
    private List<EItem> order1 = new ArrayList<EItem>();
    private List<EItem> large = new ArrayList<EItem>();

    @Before
    public void inizializeOrders() {
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

        large.add(new EItem(item.Processor, "Processore 2", 269.99));
        large.add(new EItem(item.Processor, "Processore 1", 250.00));
        large.add(new EItem(item.Processor, "Processore 3", 299.99));
        large.add(new EItem(item.Processor, "Processore 4", 349.99));
        large.add(new EItem(item.Processor, "Processore 5", 469.99));
        large.add(new EItem(item.Mouse, "Mouse 1", 14.99));
        large.add(new EItem(item.Mouse, "Mouse 2", 19.99));
        large.add(new EItem(item.Mouse, "Mouse 3", 49.99));
        large.add(new EItem(item.Mouse, "Mouse 4", 29.99));
        large.add(new EItem(item.Mouse, "Mouse 5", 89.99));
        large.add(new EItem(item.Motherboard, "SchedaMadre 1", 149.99));
        large.add(new EItem(item.Motherboard, "SchedaMadre 2", 129.99));
        large.add(new EItem(item.Motherboard, "SchedaMadre 3", 199.99));
        large.add(new EItem(item.Motherboard, "SchedaMadre 4", 249.99));
        large.add(new EItem(item.Motherboard, "SchedaMadre 5", 289.99));
        large.add(new EItem(item.Keyboard, "Tastiera 1", 89.99));
        large.add(new EItem(item.Keyboard, "Tastiera 2", 49.99));
        large.add(new EItem(item.Keyboard, "Tastiera 3", 29.99));
        large.add(new EItem(item.Keyboard, "Tastiera 4", 99.99));
        large.add(new EItem(item.Keyboard, "Tastiera 5", 119.99));
        large.add(new EItem(item.Motherboard, "SchedaMadre 4", 249.99));
        large.add(new EItem(item.Motherboard, "SchedaMadre 5", 289.99));
        large.add(new EItem(item.Keyboard, "Tastiera 1", 89.99));
        large.add(new EItem(item.Keyboard, "Tastiera 2", 49.99));
        large.add(new EItem(item.Processor, "Processore 4", 349.99));
        large.add(new EItem(item.Processor, "Processore 5", 469.99));
        large.add(new EItem(item.Mouse, "Mouse 1", 14.99));
        large.add(new EItem(item.Mouse, "Mouse 2", 19.99));
        large.add(new EItem(item.Processor, "Processore 5", 469.99));
        large.add(new EItem(item.Mouse, "Mouse 1", 14.99));
        large.add(new EItem(item.Mouse, "Mouse 3", 49.99));
        large.add(new EItem(item.Mouse, "Mouse 6", 10.00));
        large.add(new EItem(item.Mouse, "Mouse meno caro", 1));
    }


    //  #1
    @Test
    public void testTotalPrice() throws BillException{
        List<EItem> lista = new ArrayList<EItem>();
        lista.add(new EItem(EItem.item.Processor, "processore", 100));
        lista.add(new EItem(EItem.item.Processor, "processore2", 100));

        double totalPrice = BillImpl.totalPrice(lista);
        assertEquals(200, totalPrice, 0.0);
    }

    @Test
    public void testTotalPriceNullList() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.totalPrice(null);
        });
        
        assertEquals("lista null", thrown.getMessage());
    }

    //  #2
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

    // #3
    @Test
    public void testGiftCheapestMouse() {
        try {
            double cheapestMouse = BillImpl.giftCheapestMouse(large);
            assertEquals(cheapestMouse, 1, 0.0);
        } catch (BillException exception) {}
    }

    @Test
    public void testNoCheapestMouseToGift() throws BillException{
        List<EItem> not_ten_mouse = new ArrayList<EItem>();
        not_ten_mouse.add(new EItem(EItem.item.Keyboard, "non mouse", 100));

        double cheapestMouse = BillImpl.giftCheapestMouse(not_ten_mouse);
        assertEquals(cheapestMouse, 0, 0.0);
    }

    @Test
    public void testNullCheapestMouse() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.giftCheapestMouse(null);
        });
        assertEquals("lista null", thrown.getMessage());
    }

    //  #4
    @Test
    public void testGiftCheapest() {
        double cheapest = BillImpl.giftCheapest(order);
        assertEquals(cheapest, 14.99, 0);
    }

    @Test
    public void testGiftCheapestFail() {
        double cheapest = BillImpl.giftCheapest(order1);
        assertEquals(cheapest, 0, 0);
    }

    //  #5
    @Test
    public void testTenPercentDiscount() throws BillException{
        List<EItem> lista = new ArrayList<EItem>();
        lista.add(new EItem(EItem.item.Keyboard, "non mouse", 500));
        lista.add(new EItem(EItem.item.Keyboard, "tastiera 2", 550));

        double discounted_price = BillImpl.tenPercentDiscount(lista);
        assertEquals(discounted_price, 945, 0.0);
    }

    @Test
    public void testNotTenPercentDiscount() throws BillException{
        List<EItem> lista = new ArrayList<EItem>();
        lista.add(new EItem(EItem.item.Keyboard, "non mouse", 500));
        lista.add(new EItem(EItem.item.Keyboard, "tastiera 2", 400));

        double discounted_price = BillImpl.tenPercentDiscount(lista);
        assertEquals(discounted_price, 900, 0.0);
    }

    @Test
    public void testNullTenPercentDiscount() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.tenPercentDiscount(null);
        });

        assertEquals("lista null", thrown.getMessage());
    }

    //  #6
    @Test
    public void testMaxThirty() {
        try {
            BillImpl.maxThirty(large);
        } catch (BillException e) {
            assertEquals("Non è possibile avere un'ordinazione con più di 30 elementi", e.getMessage());
        }
        try {
            BillImpl.maxThirty(order);
        } catch (BillException e) {
            assertEquals("Non è possibile avere un'ordinazione con più di 30 elementi", e.getMessage());
        }
    }

    //  #8
    @Test
    public void testRndGiftCount() throws BillException {
        int count = 15;
        List<BillImpl> todayReport = new ArrayList<BillImpl>();
        while (count > 0) {
            todayReport.add(new BillImpl (order1, new User("Cod","Ing",LocalDate.of(2008,01,01)), LocalDateTime.of(2022,05,19,19,05)));
            todayReport.add(new BillImpl (order, new User("Cod","Ing",LocalDate.of(1980,01,01)), LocalDateTime.of(2022,05,22,15,25)));
            count--;
        }
        double totale = BillImpl.rndGift(todayReport);
        assertEquals(totale, 1199.9, 0.1);
    }

    @Test
    public void testRndGiftRemained() throws BillException {
        List<BillImpl> todayReport = new ArrayList<BillImpl>();
        todayReport.add(new BillImpl (order1, new User("Cod","Ing",LocalDate.of(2008,01,01)), LocalDateTime.of(2022,05,19,19,05)));
        double totale = BillImpl.rndGift(todayReport);
        assertEquals(totale, 119.99, 0.1);
    }
}

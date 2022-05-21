////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.unipd.mtss.model.EItem.item;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BillTest {
    private List<EItem> orderNull = null;
    private List<EItem> order = new ArrayList<EItem>();
    private List<EItem> order1 = new ArrayList<EItem>();
    private List<EItem> orderElementEleven = new ArrayList<EItem>();
    private List<EItem> large = new ArrayList<EItem>();
    private List<EItem> threeMouses = new ArrayList<EItem>();
    private List<EItem> moreTenMouses = new ArrayList<EItem>();
    private List<EItem> orderTest = new ArrayList<EItem>();

    private User minorUser = new User("", "", LocalDate.now().minus(14, ChronoUnit.YEARS));
    private User eighteen = new User("", "", LocalDate.now().minus(18, ChronoUnit.YEARS));

    @Before
    public void inizializeOrders() {
        order.add(new EItem(item.Processor, "Processore 2", 269.99));
        order.add(new EItem(item.Processor, "Processore 1", 250.00));
        order.add(new EItem(item.Processor, "Processore 3", 299.99));
        order.add(new EItem(item.Processor, "Processore 4", 349.99));
        order.add(new EItem(item.Processor, "Processore 5", 469.99));
        order.add(new EItem(item.Processor, "Processore 5", 469.99));

        order.add(new EItem(item.Mouse, "Mouse 2", 19.99));
        order.add(new EItem(item.Mouse, "Mouse 1", 14.99));
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

        order1.add(new EItem(item.Motherboard, "MotherboardX", 119.99));

        for (int i = 0; i < 11; i++) {
            orderElementEleven.add(new EItem(item.Keyboard, "Tastiera", 1));
        }

        for (int i = 0; i < 3; i++) {
            threeMouses.add(new EItem(item.Mouse, "MouseX", 10));
        }

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

        for(int i = 0; i < 4; i++) {
            moreTenMouses.add(new EItem(item.Mouse, "MouseX", 100));

            moreTenMouses.add(new EItem(item.Mouse, "MouseY", 50));
            
            moreTenMouses.add(new EItem(item.Mouse, "MouseZ", 25));
        }

        for (int i = 0; i < 6; i++) {
            orderTest.add(new EItem(item.Processor, "ProcessorX", 300));
        }

        for (int i = 0; i < 11; i++) {
            orderTest.add(new EItem(item.Mouse, "MouseX", 80));
        }

        for (int i = 0; i < 11; i++) {
            orderTest.add(new EItem(item.Keyboard, "KeybordX", 100));
        }
    }

    // #1
    @Test
    public void testTotalPrice() throws BillException {
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

    // #2
    @Test
    public void testScontoProcessori() throws BillException {
        double sconto = BillImpl.scontoProcessori(order);
        assertEquals(125, sconto, 0.0);
    }

    @Test
    public void testScontoProcessoriNotApplied() throws BillException {
        double sconto = BillImpl.scontoProcessori(order1);
        assertEquals(0, sconto, 0.0);
    }

    @Test
    public void testScontoProcessoriNullListException() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.scontoProcessori(orderNull);
        });

        assertEquals("lista null", thrown.getMessage());
    }

    // #3
    @Test
    public void testGiftCheapestMouse() throws BillException {
        double cheapestMouse = BillImpl.giftCheapestMouse(moreTenMouses);

        assertEquals(25, cheapestMouse, 0);
    }

    @Test
    public void testNoCheapestMouseToGift() throws BillException {
        List<EItem> not_ten_mouse = new ArrayList<EItem>();
        not_ten_mouse.add(new EItem(EItem.item.Keyboard, "non mouse", 100));

        double cheapestMouse = BillImpl.giftCheapestMouse(not_ten_mouse);
        assertEquals(0, cheapestMouse, 0.0);
    }

    @Test
    public void testNullCheapestMouse() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.giftCheapestMouse(null);
        });
        assertEquals("lista null", thrown.getMessage());
    }

    // #4
    @Test
    public void testGiftCheapest() throws BillException {
        double cheapest = BillImpl.giftCheapest(order);
        double zero = BillImpl.giftCheapest(order1);

        assertEquals(14.99, cheapest, 0);
        assertEquals(0, zero, 0);
    }

    @Test
    public void testGiftCheapestFail() throws BillException {
        double cheapest = BillImpl.giftCheapest(order1);
        assertEquals(0, cheapest, 0);
    }

    @Test
    public void testGiftCheapestNullListException() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.giftCheapest(orderNull);
        });

        assertEquals("lista null", thrown.getMessage());
    }

    // #5
    @Test
    public void testTenPercentDiscount() throws BillException {
        List<EItem> lista = new ArrayList<EItem>();
        lista.add(new EItem(EItem.item.Keyboard, "non mouse", 500));
        lista.add(new EItem(EItem.item.Keyboard, "tastiera 2", 550));

        double discounted_price = BillImpl.tenPercentDiscount(lista);
        assertEquals(945, discounted_price, 0.0);
    }

    @Test
    public void testNotTenPercentDiscount() throws BillException {
        List<EItem> lista = new ArrayList<EItem>();
        lista.add(new EItem(EItem.item.Keyboard, "non mouse", 500));
        lista.add(new EItem(EItem.item.Keyboard, "tastiera 2", 400));

        double discounted_price = BillImpl.tenPercentDiscount(lista);
        assertEquals(900, discounted_price, 0.0);
    }

    @Test
    public void testNullTenPercentDiscount() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.tenPercentDiscount(null);
        });

        assertEquals("lista null", thrown.getMessage());
    }

    // #6
    @Test
    public void testMaxThirtyException() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.maxThirty(large);
        });

        assertEquals("Non è possibile avere un ordine con più di 30 elementi", thrown.getMessage());
    }

    @Test
    public void testMaxThirtyNullListException() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.maxThirty(orderNull);
        });

        assertEquals("lista null", thrown.getMessage());
    }

    @Test
    public void testMaxThirtySuccess() throws BillException {
        boolean check = false;
        BillImpl.maxThirty(order);
        check = true;
        assertEquals(true, check);
    }

    // #7
    @Test
    public void testAddFees() throws BillException {
        List<EItem> less_than_ten = new ArrayList<EItem>();
        less_than_ten.add(new EItem(EItem.item.Keyboard, "tastiera 1", 1));
        less_than_ten.add(new EItem(EItem.item.Keyboard, "tastiera 2", 2));

        double total = BillImpl.addFees(less_than_ten);

        assertEquals(5, total, 0.0);
    }

    @Test
    public void testFailAddFees() throws BillException {
        List<EItem> less_than_ten = new ArrayList<EItem>();
        less_than_ten.add(new EItem(EItem.item.Keyboard, "tastiera 1", 10));
        less_than_ten.add(new EItem(EItem.item.Keyboard, "tastiera 2", 10));

        double total = BillImpl.addFees(less_than_ten);

        assertEquals(20, total, 0.0);
    }

    @Test
    public void testNullAddFees() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.addFees(null);
        });

        assertEquals("lista null", thrown.getMessage());
    }

    // #8
    @Test
    public void testRndGiftCounterEqualsToCount() throws BillException {
        double totale = BillImpl.rndGift(orderElementEleven, minorUser);

        assertEquals(10, totale, 0);
    }

    @Test
    public void testRndGiftCounterEqualsToRemained() throws BillException {
        double totale = BillImpl.rndGift(threeMouses, minorUser);

        assertEquals(30, totale, 0.1);
    }

    @Test
    public void testRndGiftWithoutMinorsInList() throws BillException {
        double totale = BillImpl.rndGift(threeMouses, eighteen);

        assertEquals(0, totale, 0);
    }

    @Test
    public void testRndGiftNullListException() {
        BillException thrown = assertThrows(BillException.class, () -> {
            BillImpl.rndGift(orderNull, minorUser);
        });

        assertEquals("lista null", thrown.getMessage());
    }

    //GET ORDER PRICE FUNCTION TESTS
    @Test
    public void testGetOrderPriceIllegalListException() {
        List<EItem> order = null;
        BillImpl bill = new BillImpl();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            bill.getOrderPrice(order, eighteen);
        });

        assertEquals("lista null", thrown.getMessage());
    }

    @Test
    public void testGetOrderPriceIllegalUserException() {
        User user = null;
        BillImpl bill = new BillImpl();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            bill.getOrderPrice(order, user);
        });

        assertEquals("Utente non valido", thrown.getMessage());
    }

    @Test
    public void testGetOrderPrice() throws BillException, IllegalArgumentException {
        BillImpl bill = new BillImpl();

        double price = bill.getOrderPrice(orderTest, eighteen);

        assertEquals(3848, price, 0);
    }
}
////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import java.time.LocalTime;
import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {

    // Dato un elenco di articoli (Processori, Schede Madri, Tastiere, Mouse) calcolare il totale
    private double totalPrice(List<EItem> items) throws BillException{
        double total = 0;
        if (items != null) {
            for (EItem item: items) {
                total = total + item.getPrice();
            }
        } else {
            throw new BillException("lista null");
        }
        return total;
    }

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user, LocalTime orderTime) throws BillException {
        return totalPrice(itemsOrdered);
    }
}
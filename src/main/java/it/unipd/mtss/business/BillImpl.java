////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ListIterator;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {

    private List<EItem> _list;
    private User _user;
    private LocalDate _date;

    // costruttore
    // public BillImpl(List<EItem> list, User user, LocalDate date){
    // this._list = list;
    // this._user = user;
    // this._date = date;
    // }

    // Dato un elenco di articoli (Processori, Schede Madri, Tastiere, Mouse)
    // calcolare il totale #1
    private double totalPrice(List<EItem> itemsOrdered) throws BillException {
        double total = 0;
        if (itemsOrdered != null) {
            for (EItem item : itemsOrdered) {
                total = total + item.getPrice();
            }
        } else {
            throw new BillException("lista null");
        }
        return total;
    }

    // Se vengono ordinati più di 5 Processore viene fatto uno sconto del 50% sul
    // prezzo del Processori meno caro #2
    public static double scontoProcessori(List<EItem> itemsOrdered) {
        int count = 0;
        double cheapest = Double.POSITIVE_INFINITY;
        for (EItem it : itemsOrdered) {
            if (it.getItemType() == EItem.item.Processor) {
                count++;
                if (cheapest == Double.POSITIVE_INFINITY || cheapest > it.getPrice()) {
                    cheapest = it.getPrice();
                }
            }
        }

        if (count >= 5) {
            return cheapest / 2;
        }

        return 0;
    }

    // Se vengono ordinati lo stesso numero di Mouse e Tastiere viene regalato
    // l’articolo meno caro #4
    public static double giftCheapest(List<EItem> itemsOrdered) {
        int countMouses = 0, countKeyboards = 0;
        double cheapest = Double.POSITIVE_INFINITY;
        for (EItem it : itemsOrdered) {
            if (it.getItemType() == EItem.item.Mouse) {
                countMouses++;
            }

            if (it.getItemType() == EItem.item.Keyboard) {
                countKeyboards++;
            }

            if (cheapest == Double.POSITIVE_INFINITY || cheapest > it.getPrice()) {
                cheapest = it.getPrice();
            }
        }

        if (countMouses == countKeyboards) {
            return cheapest;
        }

        return 0;
    }

    // Non è possibile avere un’ordinazione con più di 30 elementi (se accade
    // prevedere un messaggio d’errore) #6
    public static void maxThirty(List<EItem> itemsOrdered) throws BillException {
        if (itemsOrdered.size() > 30) {
            throw new BillException("Non è possibile avere un'ordinazione con più di 30 elementi");
        }
    }

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        return totalPrice(itemsOrdered);
    }
}

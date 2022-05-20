////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {

    private List<EItem> _list;
    private User _user;
    private LocalDateTime _date;

    /**************************************************************************************
     ***** Costruttore
     **************************************************************************************/
    public BillImpl(List<EItem> list, User user, LocalDateTime date) {
        this._list = list;
        this._user = user;
        this._date = date;
    }

    /**************************************************************************************
     ***** Dato un elenco di articoli (Processori, Schede Madri, Tastiere, Mouse)
     ***** calcolare il totale #1
     **************************************************************************************/
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

    /**************************************************************************************
     ***** Se vengono ordinati più di 5 Processore viene fatto uno sconto del 50% sul
     ***** prezzo del Processori meno caro #2
     **************************************************************************************/
    public static double scontoProcessori(List<EItem> itemsOrdered) throws BillException {
        if (itemsOrdered != null) {
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
        } else {
            throw new BillException("lista null");
        }

        return 0;
    }

    /**************************************************************************************
     ***** Se vengono ordinati lo stesso numero di Mouse e Tastiere viene regalato
     ***** l’articolo meno caro #4
     **************************************************************************************/
    public static double giftCheapest(List<EItem> itemsOrdered) throws BillException {
        if (itemsOrdered != null) {
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
        } else {
            throw new BillException("lista null");
        }
        return 0;
    }

    /**************************************************************************************
     ***** Non è possibile avere un’ordinazione con più di 30 elementi (se accade
     ***** prevedere un messaggio d’errore) #6
     **************************************************************************************/
    public static void maxThirty(List<EItem> itemsOrdered) throws BillException {
        if(itemsOrdered != null) {
            if (itemsOrdered.size() > 30) {
                throw new BillException("Non è possibile avere un'ordinazione con più di 30 elementi");
            }
        } else {
            throw new BillException("lista null");
        }
    }

    /*************************************************************************************
     **** Prevedere la possibilità di regalare, in modo casuale, 10 ordini effettuati
     **** dalle 18:00 alle 19:00 da utenti minorenni differenti. #8
     **************************************************************************************/
    public static double rndGift(List<BillImpl> todayReport) throws BillException {
        double totale = 0;
        if(todayReport != null) {
            int count = 10;
            List<BillImpl> aux = new ArrayList<BillImpl>();
            
            for (BillImpl it : todayReport) {
                if (it._user.getDate_of_birth().isAfter(LocalDate.now().minus(18, ChronoUnit.YEARS))) {
                    aux.add(it);
                }
            }
            
            int remained = aux.size();
            
            while (remained > 0 && count > 0) {
                SecureRandom rand = new SecureRandom();
                BillImpl randomElement = aux.get(rand.nextInt(remained));
                totale += randomElement.getOrderPrice(randomElement._list, randomElement._user);
                remained--;
                count--;
            }

        } else {
            throw new BillException("Nessun ordine ricevuto per oggi.");
        }
        return totale;
    }

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        return totalPrice(itemsOrdered);
    }
}

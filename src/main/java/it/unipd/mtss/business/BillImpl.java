////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {

    /**************************************************************************************
     ***** Dato un elenco di articoli (Processori, Schede Madri, Tastiere, Mouse)
     ***** calcolare il totale #1
     **************************************************************************************/
    public static double totalPrice(List<EItem> itemsOrdered) throws BillException {
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
     ***** Se vengono ordinati più di 5 Processori viene fatto uno sconto del 50% sul
     ***** prezzo del Processore meno caro #2
     **************************************************************************************/
    public static double scontoProcessori(List<EItem> itemsOrdered) throws BillException {
        if (itemsOrdered == null) {
            throw new BillException("lista null");
        }
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

        if (count > 5) {
            return cheapest / 2;
        }

        return 0;
    }

    /**************************************************************************************
     ***** Se vengono ordinati più di 10 Mouse il meno caro viene regalato
     ***** #3
     **************************************************************************************/
    public static double giftCheapestMouse(List<EItem> orders) throws BillException {
        int counter = 0;
        double cheapest = Double.POSITIVE_INFINITY;

        if (orders == null) {
            throw new BillException("lista null");
        }

        for (EItem item : orders) {
            if (item.getItemType() == EItem.item.Mouse) {
                counter++;
                if (cheapest == Double.POSITIVE_INFINITY || cheapest > item.getPrice()) {
                    cheapest = item.getPrice();
                }
            }

            if (counter >= 10) {
                return cheapest;
            }
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

            if (countMouses + countKeyboards != 0 && countMouses == countKeyboards) {
                return cheapest;
            }
        } else {
            throw new BillException("lista null");
        }
        return 0;
    }

    /**************************************************************************************
     ***** Se l’importo totale degli articoli supera i 1000 euro viene
     ***** fatto uno sconto del 10% sul totale; #5
     **************************************************************************************/
    public static double tenPercentDiscount(List<EItem> orders) throws BillException {
        double totalPrice = 0;
        if (orders == null) {
            throw new BillException("lista null");
        }
        for (EItem item : orders) {
            totalPrice += item.getPrice();
        }

        if (totalPrice > 1000) {
            return totalPrice - (totalPrice * 0.1);
        }

        return totalPrice;
    }

    /**************************************************************************************
     ***** Non è possibile avere un’ordinazione con più di 30 elementi (se accade
     ***** prevedere un messaggio d’errore) #6
     **************************************************************************************/
    public static void maxThirty(List<EItem> itemsOrdered) throws BillException {
        if (itemsOrdered != null) {
            if (itemsOrdered.size() > 30) {
                throw new BillException("Non è possibile avere un ordine con più di 30 elementi");
            }
        } else {
            throw new BillException("lista null");
        }
    }

    /**************************************************************************************
     ***** Se l’importo totale è inferiore a 10 € viene aggiunta una commissione di 2 €
     ***** #7
     **************************************************************************************/
    public static double addFees(List<EItem> orders) throws BillException {
        double total = 0;
        if (orders == null) {
            throw new BillException("lista null");
        }
        for (EItem item : orders) {
            total += item.getPrice();
        }

        if (total < 10) {
            return total + 2;
        }
        return total;
    }

    /*************************************************************************************
     **** Prevedere la possibilità di regalare, in modo casuale, 10 ordini effettuati
     **** dalle 18:00 alle 19:00 da utenti minorenni differenti. #8
     **************************************************************************************/
    public static double rndGift(List<EItem> itemsOrdered, User user) throws BillException, IllegalArgumentException {
        double totale = 0;
        if (itemsOrdered == null) {
            throw new BillException("lista null");
        }
        List<EItem> aux = new ArrayList<EItem>();

        if (user.getDateOfBirth().isAfter(LocalDate.now().minus(18, ChronoUnit.YEARS))) {
            for (EItem it : itemsOrdered) {
                aux.add(it);
            }
        }

        int count = 10;
        int remained = aux.size();

        while (remained > 0 && count > 0) {
            SecureRandom rand = new SecureRandom();
            EItem randomElement = aux.get(rand.nextInt(remained));
            totale += randomElement.getPrice();
            aux.remove(randomElement);
            remained--;
            count--;
        }
        return totale;
    }

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException, IllegalArgumentException {
        if (itemsOrdered == null) {
            throw new IllegalArgumentException("lista null");
        } else if (user == null) {
            throw new IllegalArgumentException("Utente non valido");
        }

        // #6
        maxThirty(itemsOrdered);

        // #1, #2, #3, #4, #5, #7, #8
        return totalPrice(itemsOrdered) - scontoProcessori(itemsOrdered) - giftCheapestMouse(itemsOrdered)
                - giftCheapest(itemsOrdered) - tenPercentDiscount(itemsOrdered) + addFees(itemsOrdered)
                - rndGift(itemsOrdered, user);
    }
}
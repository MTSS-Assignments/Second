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

public class BillImpl implements Bill{

    private List<EItem> _list;
    private User _user;
    private LocalDate _date;

    // public BillImpl(List<EItem> list, User user, LocalDate date){
    //     this._list = list;
    //     this._user = user;
    //     this._date = date;
    // }

    public static double scontoProcessori(List<EItem> ordine){
        int count = 0;
        double cheapest = Double.POSITIVE_INFINITY;
        for (EItem it : ordine){
            if (it.getItemType() == EItem.item.Processor) { 
                count++; 
                if (cheapest == Double.POSITIVE_INFINITY || cheapest > it.getPrice()) { cheapest = it.getPrice(); }
            }
        }

        if (count >= 5) {return  cheapest / 2; }
        
        return 0;
    }

    public double getOrderPrice(List<EItem> itemsOrdered, User user) 
            throws BillException{
                return 0;
            }
}

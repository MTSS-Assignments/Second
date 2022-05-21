////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

public class EItem {
    public enum item {
        Processor,
        Motherboard,
        Mouse,
        Keyboard
    };

    private item itemType;
    private String name;
    private double price;

    public EItem(item itType, String n, double p) {
        if (itType != null){
            this.itemType = itType;
        } else {
            throw new IllegalArgumentException("Il campo itType non può essere null");
        }

        if (n != null &&  !n.equals("")){
            this.name = n;
        } else {
            throw new IllegalArgumentException("Il campo name non può essere vuoto");
        }
        
        if (p >= 0){
            this.price = p;
        } else {
            throw new IllegalArgumentException("Il prezzo deve avre un valore >= 0");
        }
    }

    public item getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
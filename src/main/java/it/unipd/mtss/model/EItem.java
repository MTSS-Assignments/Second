////////////////////////////////////////////////////////////////////
// Marco Bustaffa 1226301
// Luca Busacca 1227589
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

public class EItem {
    private enum item {
        Processor,
        Motherboard,
        Mouse,
        Keyboard
    };

    private item itemType;
    private String name;
    private double price;

    public EItem(item itType, String n, double p) {
        this.itemType = itType;
        this.name = n;
        if (p >= 0)
            this.price = p;
        else
            throw new IllegalArgumentException("Il prezzo deve avre un valore >= 0");
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
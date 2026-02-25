package Hrac;

import java.util.ArrayList;

/**
 * Třída reprezentuje inventář hráče.
 * Inventář má omezenou kapacitu.
 *
 * @author Ondrej Soukup
 */

public class Inventory {
    private ArrayList<Item> items = new ArrayList<>();
    private int kapacita;

    /**
     * Vytvori inventar s danou kapacitou
     *
     * @param kapacita maximální počet předmětů
     */

    public Inventory(int kapacita) {
        this.kapacita = kapacita;
    }

    /**
     * prida predmet do inventare
     *
     * @param item předmět
     * @return true pokud byl přidán
     */

    public boolean addItem(Item item){
        if(items.size() >= kapacita){
            return false;
        }
        items.add(item);
        return true;
    };
    public boolean removeIteam(Item itemId){
        for(Item item : items){
            if(item.getId().equals(itemId)){
                items.remove(item);
                return true;
            }
        }
        return false;
    };

    /**
     * vrati predmet podle ID
     *
     * @param id identifikátor předmětu
     * @return předmět nebo null
     */
    public Item getItem(String id){
        for(Item item : items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }
}

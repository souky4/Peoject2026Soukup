package Hrac;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<>();
    private int kapacita;

    public Inventory(int kapacita) {
        this.kapacita = kapacita;
    }

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
    public Item getItem(String id){
        for(Item item : items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }
    public List<Item> showInventory(){
        return items;
    };

    public int getCurrentSize(){
        return items.size();
    }
}

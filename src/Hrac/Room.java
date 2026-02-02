package Hrac;

import Characters.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private String jmeno;
    private String popis;
    private Map<String, Room> exits;
    private ArrayList<Item> items;
    private ArrayList<Character> characters;

    public Room(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
    }

    public String getJmeno() {return jmeno;};
    public String getPopis (){return popis;}
    public Room getExit(String smer) {return exits.get(smer);};
    public void addExit(String smer, Room room){ exits.put(smer,room );};
    public void addItem (Item item){items.add(item);};
    public void removeItem(Item item){items.remove(item);};
    public ArrayList<Item> getItems(){return items;};
    public ArrayList<Character> getCharacters() {return characters;};
}

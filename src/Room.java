import Characters.Character;

import java.util.ArrayList;
import java.util.Map;

public class Room {
    private String jmeno;
    private String popis;
    private Map<String, Room> exits;
    private ArrayList<Item> items;
    private ArrayList<Character> characters;

    public String getJmeno() {};
    public Room getExit(String smer) {};
    public void addExit(String smer, Room room){};
    public void addItem (Item item){};
    public void removeItem(Item item){};
    public void popis(){};


}

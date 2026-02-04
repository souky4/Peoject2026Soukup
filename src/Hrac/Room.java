package Hrac;

import java.util.*;

public class Room {

    private String jmeno;
    private String popis;
    private String id;
    private Map<String, Room> exits;
    private List<Item> items;
    private List<Character> characters;

    public Room(String id,String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.id = id;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return popis;
    }

    public Room getExit(String smer) {
        return exits.get(smer);
    }

    public void addExit(String smer, Room room) {
        exits.put(smer, room);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getId(){return id;}

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }
}
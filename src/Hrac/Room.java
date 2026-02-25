package Hrac;
import java.util.*;
/**
 * Třída reprezentuje místnost ve hře.
 * Obsahuje identifikátor, název, popis, seznam východů,
 * předmětů a postav nacházejících se v místnosti.
 *
 * @author Ondrej Soukup
 */
public class Room {

    private String jmeno;
    private String popis;
    private String id;
    private Map<String,Room> exit;

    private List<Item> items;
    private List<Character> characters;

    /**
     *  Vytvori novou mistnost
     *
     * @param id identifikátor místnosti
     * @param jmeno nazev moistnosti
     * @param popis textovy popis mistnosti
     */

    public Room(String id, String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.id = id;
        this.exit = new HashMap<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return popis;
    }

    /**
     * Vratí mistnost ve zvolenem smeru
     *
     * @param smer směr pohybu
     * @return cilova mistnost nebo null
     */

    public Room getExit(String smer) {
        return exit.get(smer);
    }

    /**
     * Prida vychod do jiné místnosti
     *
     * @param smer směr
     * @param room cílová místnost
     */

    public void addExit(String smer, Room room) {
        exit.put(smer, room);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * vrati textovy seznam dostupnych smeru
     *
     * @return  řetězec s východy oddělenými čárkou
     */

    public String getExitNames(){return String.join(", ", exit.keySet());}

    public String getId(){return id;}

    /**
     * Odebere predmet z mistnosti
     *
     * @param item danny item v seznamu
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param npcId identifikátor postavy
     * @return nalezené NPC nebo null
     */

    public NPC getNpc(String npcId) {
        for (Character character : characters) {
            if(character instanceof NPC npc && character.getId().equals(npcId)) {
                return npc;
            }

        }
        return null;
    }

    /**
     * najde predmet podle jeho id
     *
     * @param itemId identifikátor předmětu
     * @return nalezený předmět nebo null
     */

    public Item getItem(String itemId){
        for(Item item : items){
            if(item.getId().equals(itemId)){
                return item;
            }
        }
        return null;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }
}
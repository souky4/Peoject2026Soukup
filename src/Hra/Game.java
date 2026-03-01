package Hra;


import Hrac.Character;
import Hrac.Inventory;
import Hrac.Item;
import Hrac.NPC;
import Hrac.Room;

import java.util.Scanner;
/**
 * Hlavní třída hry.
 * Řídí herní smyčku, zpracování vstupu a výstup do konzole.
 *
 * @author Ondrej Soukup
 */

public class Game {
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String MAGENTA = "\u001B[35m";


    private World world;
    private Room currentRoom;
    private Inventory inventory;
    private SpravcePrikazu spravceprikazu;
    private boolean isRunning;
    private boolean unlockedTrunnisal = false;

    public Game() {
        WorldLoader loader = new WorldLoader();
        world = loader.loadWorld("world.json");

        currentRoom = world.getStartingRoom();
        inventory = new Inventory(3);
        spravceprikazu = new SpravcePrikazu();
        isRunning = true;
    }

    /**
     * spusti herni smycku
     */
    public void Start(){
        printWelcome();
        showCurrentRoom();
        showMap();

        Scanner scanner = new Scanner(System.in);

        while(isRunning){
            String input = scanner.nextLine();
            spravceprikazu.provedPrikaz(input,this);
            if(isRunning){
                showMap();
            }
        }
    }

    /**
     * zjisti jestli hrac vzhral
     */
    private void checkWinCondition(){
        if(currentRoom.getId().equals("vchod")&& inventory.getItem("krystal")!=null){
            printMessage("");
            printMessage("✨ Získal jsi Magický krystal!");
            printMessage("✨ Vrátil ses zpět na vchod pevnosti.");
            printMessage("🎉 Vyhrál jsi hru!");
            isRunning = false;
        };
    }

    /**
     * vypis jednotlivych textu
     *
     * @param text text ktery se ma vypsat
     */
    public void printMessage(String text){
        System.out.println(text);
    }

    /**
     * vypise veskere informace o mistnosti kde se hrac prave nachazi
     */
    public void showCurrentRoom() {
        printMessage("");
        printMessage(BOLD + CYAN + "════════════════════════════════════" + RESET);
        printMessage(BOLD  + currentRoom.getJmeno() + RESET);
        printMessage(currentRoom.getPopis());

        if (!currentRoom.getItems().isEmpty()) {
            printMessage(YELLOW + " Předměty:" + RESET);
            for (Item item : currentRoom.getItems()) {
                printMessage("- " + item.getNazev() + " (" + item.getId() + ")");
            }
        }
        if (!currentRoom.getCharacters().isEmpty()) {
            printMessage(MAGENTA + " Postavy:" + RESET);
            for (Character npc : currentRoom.getCharacters()) {
                if (npc instanceof NPC) {
                    printMessage("- " + npc.getJmeno() + " (" + npc.getId() + ")");
                }
            }

        }
        printMessage(GREEN + " Východy: " + currentRoom.getExitNames() + RESET);
        printMessage(BOLD + CYAN + "════════════════════════════════════" + RESET);


    }
    /**
     * vypise jednoduchou mapu hry a zvyrazni aktualni mistnost
     */
    public void showMap() {
        printMessage("");
        printMessage(BOLD + "🗺️ MAPA PEVNOSTI" + RESET);
        printMessage("  " + roomLabel("knihovna"));
        printMessage("      │");
        printMessage(roomLabel("vchod") + " ── " + roomLabel("hlavni_chodba") + " ── " + roomLabel("zbrojnice"));
        printMessage("               │");
        printMessage("         " + roomLabel("straznice"));
        printMessage("               │");
        printMessage("         " + roomLabel("trunni_sal"));
        printMessage("               │");
        printMessage("   " + roomLabel("alchymisticka_laborator"));
        printMessage("               │");
        printMessage("            " + roomLabel("kaple"));
    }

    private String roomLabel(String roomId) {
        Room room = world.getRoom(roomId);
        if (room == null) {
            return "[?]";
        }

        String label = "[" + room.getJmeno() + "]";
        if (currentRoom.getId().equals(roomId)) {
            return BOLD + GREEN + "➡ " + label + RESET;
        }

        return label;


    }


    /**
     * Pohyb hrace do jine mistnosti
     *
     * @param direction smer pohybu
     */

    public void moveTo(String direction){
        if (currentRoom.getId().equals("straznice") && direction.equals("jih") && !unlockedTrunnisal) {
            printMessage("Dveře jsou zamčené. Možná by pomohl klíč.");
            return;
        }

        Room next = currentRoom.getExit(direction);
        if (next == null) {
            printMessage("Tímto směrem se jít nedá.");
            return;
        }

        currentRoom = next;
        showCurrentRoom();
        checkWinCondition();
    }

    /**
     * vypis uvodniho textu
     */
    public void printWelcome(){
        printMessage("Vítej ve hře!");
        printMessage("Cíl: najdi 'magicky_krystal' a vrať se na vchod.");
        printMessage("Napiš 'pomoc' pro seznam příkazů.");
    }

    /**
     * seber predmet z mistnosti
     *
     * @param itemId identifikátor předmětu
     */

    public void takeItem(String itemId){
        Item item = currentRoom.getItem(itemId);

        if(item == null){
            printMessage("Takový předmět tu není.");
            return;
        }

        if(!item.jePrenosny()){
            printMessage("Tento předmět nelze sebrat.");
            return;
        }

        if(!inventory.addItem(item)){
            printMessage("Inventář je plný.");
            return;
        }

        currentRoom.removeItem(item);
        printMessage("Sebral jsi: " + item.getNazev());
    }

    /**
     * polozi predmet do mistnosti
     *
     * @param itemId identifikátor předmětu
     */

    public void dropItem(String itemId){
        Item item = inventory.getItem(itemId);
        if (item == null) {
            printMessage("Takový předmět nemáš.");
            return;
        }
        inventory.removeIteam(item);
        currentRoom.addItem(item);
        printMessage("Položil jsi: " + item.getNazev());
    }

    /**
     * pouzije predmet inventare
     *
     * @param itemId identifikátor předmětu
     */

    public void useItem(String itemId){
        Item item = inventory.getItem(itemId);
        if(item == null){
            printMessage("Takový předmět nemáš v inventáři.");
            return;
        }
        switch (itemId){
            case"klic":
                unlockedTrunnisal = true;
                printMessage("Použil jsi klíč. Slyšíš cvaknutí zámku.");
                break;
            case "pochoden":
                printMessage("Zapálil jsi pochodeň. Vidíš více detailů v okolí.");
                printMessage(getHintText()); // jednoduchý efekt = nápověda
                break;
            case "svitek":
                printMessage("Přečetl jsi svitek. Nápověda:");
                printMessage(getHintText());
                break;
            default:
                printMessage("Tento předmět zatím nejde použít.");
                break;
        }
    }

    /**
     * promluvi s NPC v mkstnosti
     *
     * @param npcId identifikátor postavy
     */
    public void talkTo(String npcId){
        NPC npc = currentRoom.getNpc(npcId);
        if (npc == null) {
            printMessage("Nikdo takový tu není.");
            return;
        }
        printMessage(npc.getJmeno() + ": " + npc.getDialog());
    }

    /**
     * ukaze seznam prikazu
     */
    public void showHelp() {
        printMessage("Příkazy:");
        printMessage("- jdi <smer>");
        printMessage("- prozkoumat");
        printMessage("- seber <nazev Itemu>");
        printMessage("- poloz <nazev Itemu>");
        printMessage("- inventar");
        printMessage("- pouzij <nazev Itemu>");
        printMessage("- promluv <Jmeno Npc>");
        printMessage("- napoveda");
        printMessage("- pomoc");
        printMessage("- konec");
    }
    public void showHint() {
        printMessage(getHintText());
    }

    /**
     * vzpise napovedu podle toho kde se nachazi
     *
     * @return text s napovedou
     */
    private String getHintText() {
        switch (currentRoom.getId()) {
            case "knihovna":
                return "Tip: V knihovně se často skrývají důležité informace nebo svitek.";
            case "straznice":
                return "Tip: Pokud tě něco blokuje, zkus použít předmět (např. klíč).";
            case "trunni_sal":
                return "Tip: Hledej něco cenného. Krystal může být blízko.";
            default:
                return "Tip: Prozkoumávej místnosti a sbírej užitečné předměty.";
        }
    }

    /**
     * ukonci hru
     */
    public void endGame() {
        isRunning = false;
        printMessage("Hra ukončena.");
    }




}

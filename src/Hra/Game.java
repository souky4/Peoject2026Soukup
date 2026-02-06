package Hra;


import Hrac.Character;
import Hrac.Inventory;
import Hrac.Item;
import Hrac.NPC;
import Hrac.Room;

import java.util.Scanner;

public class Game {

    private World world;
    private Room currentRoom;
    private Inventory inventory;
    private SpravcePrikazu spravceprikazu;
    private boolean isRunning;

    public Game() {
        WorldLoader loader = new WorldLoader();
        world = loader.loadWorld("world.json");

        currentRoom = world.getStartingRoom();
        inventory = new Inventory(3);
        spravceprikazu = new SpravcePrikazu();
        isRunning = true;
    }
    public void Start(){
        printWelcome();
        showCurrentRoom();

        Scanner scanner = new Scanner(System.in);

        while(isRunning){
            String input = scanner.nextLine();
            spravceprikazu.provedPrikaz(input,this);
        }
    }



    private void checkWinCondition(){
        if(currentRoom.getId().equals("vchod")&& inventory.getItem("magicky_krystal")!=null){
            printMessage("");
            printMessage("✨ Získal jsi Magický krystal!");
            printMessage("✨ Vrátil ses zpět na vchod pevnosti.");
            printMessage("🎉 Vyhrál jsi hru!");
            isRunning = false;
        };
    }


    public void printMessage(String text){
        System.out.println(text);
    }

    public void showCurrentRoom() {
        printMessage("");
        printMessage(currentRoom.getJmeno());
        printMessage(currentRoom.getPopis());

        if (!currentRoom.getItems().isEmpty()) {
            printMessage("Předměty:");
            for (Item item : currentRoom.getItems()) {
                printMessage("- " + item.getNazev() + " (" + item.getId() + ")");
            }
        }
        if (!currentRoom.getCharacters().isEmpty()) {
            printMessage("Postavy:");
            for (Character npc : currentRoom.getCharacters()) {
                if (npc instanceof NPC) {
                    printMessage("- " + npc.getJmeno() + " (" + npc.getId() + ")");
                }
            }

        }
        printMessage("Východy: " + currentRoom.getExitNames());


    }

    public void moveTo(String direction){
        if (currentRoom.getId().equals("straznice") && direction.equals("jih") && !unlockedTrunniSal) {
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

    public void printWelcome(){
        printMessage("Vítej ve hře!");
        printMessage("Cíl: najdi 'magicky_krystal' a vrať se na vchod.");
        printMessage("Napiš 'pomoc' pro seznam příkazů.");
    }

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



}

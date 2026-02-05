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



}

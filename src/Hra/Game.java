package Hra;


import Hrac.Inventory;
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
            spravceprikazu.provedPrikaz(input);
        }
    }

    public void moveTo(String smer){
        Room next = currentRoom.getExit(smer);

        if(next == null){
            printMessage("Tímto směrem se jít nedá");
            return;
        }

        currentRoom = next;
        showCurrentRoom();
        checkWinCondition();
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



}

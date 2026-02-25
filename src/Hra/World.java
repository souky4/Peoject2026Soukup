package Hra;

import Hrac.Room;

import java.util.*;
/**
        * Třída reprezentuje herní svět.
        * Uchovává všechny místnosti a počáteční místnost.
        *
        * @author Ondrej Soukup
 */

public class World {

    private Map<String, Room> rooms;
    private Room startingRoom;

    public World() {
        rooms = new HashMap<>();
    }

    /**
     * Prida mistnost do sveta
     *
     * @param room Místnost
     */

    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    /**
     * Vrati mistnost podle ID
     *
     * @param id identifikátor místnosti
     * @return nalezená místnost nebo null
     */

    public Room getRoom(String id) {
        return rooms.get(id);
    }

    /**
     * Nastavi pocatecni mistnost
     *
     * @param room startovni mistnost
     */

    public void setStartingRoom(Room room) {
        this.startingRoom = room;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }



}
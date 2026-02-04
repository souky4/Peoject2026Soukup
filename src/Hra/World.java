package Hra;

import Hrac.Room;

import java.util.*;

public class World {

    private Map<String, Room> rooms;
    private Room startingRoom;

    public World() {
        rooms = new HashMap<>();
    }

    public void addRoom(Room room) {
        rooms.put(room.getJmeno(), room);
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public void setStartingRoom(Room room) {
        this.startingRoom = room;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }

    public Collection<Room> getAllRooms() {
        return rooms.values();
    }
}
import Hrac.Room;

import java.util.HashMap;
import java.util.Map;

public class World {
    private Map<String, Room> rooms;
    private Room startingRoom;

    public World() {
        rooms = new HashMap<>();
    }

    public void addRoom(Room room) {rooms.put(room.getJmeno(), room);};
    public Room getRoom(String roomName) {return rooms.get(roomName);};
    public void setStartingRoom(Room room){startingRoom = room;};
    public Room getStartingRoom(){return startingRoom;};
}

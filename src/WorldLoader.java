
import Hrac.Character;
import Hrac.Item;
import Hrac.Room;
import com.google.gson.*;
import java.io.*;
import java.util.Map;

public class WorldLoader {

    public World loadWorld(String filePath) {
        World world = new World();

        JsonObject json = loadJson(filePath);

        loadRooms(world, json);
        connectRooms(world, json);
        loadItems(world, json);
        loadCharacters(world, json);
        setStartingRoom(world, json);

        return world;
    }

    private JsonObject loadJson(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Nepodařilo se načíst JSON soubor");
        }
    }

    private void loadRooms(World world, JsonObject json) {
        JsonArray rooms = json.getAsJsonArray("rooms");

        for (JsonElement element : rooms) {
            JsonObject r = element.getAsJsonObject();
            String name = r.get("name").getAsString();
            String description = r.get("description").getAsString();

            world.addRoom(new Room(name, description));
        }
    }


    private void connectRooms(World world, JsonObject json) {
        JsonArray rooms = json.getAsJsonArray("rooms");

        for (JsonElement element : rooms) {
            JsonObject r = element.getAsJsonObject();
            Room room = world.getRoom(r.get("name").getAsString());

            JsonObject exits = r.getAsJsonObject("exits");
            for (Map.Entry<String, JsonElement> exit : exits.entrySet()) {
                String smer = exit.getKey();
                String targetName = exit.getValue().getAsString();
                room.addExit(smer, world.getRoom(targetName));
            }
        }
    }

    private void loadItems(World world, JsonObject json) {
        JsonArray rooms = json.getAsJsonArray("rooms");

        for (JsonElement element : rooms) {
            JsonObject r = element.getAsJsonObject();
            Room room = world.getRoom(r.get("name").getAsString());

            if (!r.has("items")) continue;

            for (JsonElement itemEl : r.getAsJsonArray("items")) {
                JsonObject i = itemEl.getAsJsonObject();
                room.addItem(new Item(
                        i.get("name").getAsString(),
                        i.get("description").getAsString(),
                        i.get("portable").getAsBoolean()
                ));
            }
        }
    }

    private void loadCharacters(World world, JsonObject json) {
        JsonArray rooms = json.getAsJsonArray("rooms");

        for (JsonElement element : rooms) {
            JsonObject r = element.getAsJsonObject();
            Room room = world.getRoom(r.get("name").getAsString());

            if (!r.has("characters")) continue;

            for (JsonElement chEl : r.getAsJsonArray("characters")) {
                JsonObject c = chEl.getAsJsonObject();
                room.addCharacter(new Character(
                        c.get("name").getAsString(),
                        c.get("description").getAsString(),
                        c.get("dialog").getAsString()
                ));
            }
        }
    }

    private void setStartingRoom(World world, JsonObject json) {
        String start = json.get("start").getAsString();
        world.setStartingRoom(world.getRoom(start));
    }
}
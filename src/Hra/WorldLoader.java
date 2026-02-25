package Hra;

import Hrac.Character;
import Hrac.Item;
import Hrac.NPC;
import Hrac.Room;
import com.google.gson.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);

        if (is == null) {
            throw new RuntimeException("Soubor '" + filePath + "' nebyl nalezen v resources.");
        }

        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Nepodařilo se načíst JSON: " + filePath, e);
        }
    }

    private void loadRooms(World world, JsonObject json) {
        JsonArray roomsJson = json.getAsJsonArray("mistnosti");
        if (roomsJson == null) {
            throw new RuntimeException("V JSON chybí pole 'mistnosti'.");
        }

        for (JsonElement element : roomsJson) {
            JsonObject roomJson = element.getAsJsonObject();

            String id = getRequiredString(roomJson, "id");
            String nazev = getRequiredString(roomJson, "nazev");
            String popis = getRequiredString(roomJson, "popis");

            Room room = new Room(id, nazev, popis);
            world.addRoom(room);
        }
    }


    private void connectRooms(World world, JsonObject json) {
        JsonArray roomsJson = json.getAsJsonArray("mistnosti");

        for (JsonElement element : roomsJson) {
            JsonObject roomJson = element.getAsJsonObject();

            String roomId = getRequiredString(roomJson, "id");
            Room room = world.getRoom(roomId);

            JsonObject exitsJson = roomJson.getAsJsonObject("exits");
            if (exitsJson == null) continue;

            for (Map.Entry<String, JsonElement> exit : exitsJson.entrySet()) {
                String direction = exit.getKey().toLowerCase();
                String targetId = exit.getValue().getAsString();

                Room targetRoom = world.getRoom(targetId);
                if (targetRoom == null) {
                    throw new RuntimeException("Místnost '" + roomId + "' má exit na neexistující id: " + targetId);
                }

                room.addExit(direction, targetRoom);
            }
        }
    }

    private void loadItems(World world, JsonObject json) {
        JsonArray roomsJson = json.getAsJsonArray("mistnosti");

        for (JsonElement element : roomsJson) {
            JsonObject roomJson = element.getAsJsonObject();

            String roomId = getRequiredString(roomJson, "id");
            Room room = world.getRoom(roomId);

            JsonArray itemsJson = roomJson.getAsJsonArray("items");
            if (itemsJson == null) continue;

            for (JsonElement itemEl : itemsJson) {
                JsonObject itemJson = itemEl.getAsJsonObject();

                String id = getRequiredString(itemJson, "id");
                String nazev = getRequiredString(itemJson, "nazev");
                String popis = getRequiredString(itemJson, "popis");

                // portable je volitelné (když není, bereme true)
                boolean portable = itemJson.has("portable") && itemJson.get("portable").isJsonPrimitive()
                        ? itemJson.get("portable").getAsBoolean()
                        : true;

                Item item = new Item(nazev, id, portable,popis);
                room.addItem(item);
            }
        }
    }

    private void loadCharacters(World world, JsonObject json) {
        JsonArray roomsJson = json.getAsJsonArray("mistnosti");

        for (JsonElement element : roomsJson) {
            JsonObject roomJson = element.getAsJsonObject();

            String roomId = getRequiredString(roomJson, "id");
            Room room = world.getRoom(roomId);

            JsonArray charsJson = roomJson.getAsJsonArray("characters");
            if (charsJson == null) continue;

            for (JsonElement chEl : charsJson) {
                JsonObject chJson = chEl.getAsJsonObject();

                String id = getRequiredString(chJson, "id");
                String jmeno = getRequiredString(chJson, "jmeno");

                // dialog je volitelný
                String dialog = chJson.has("dialog") ? chJson.get("dialog").getAsString() : "";

                Character npc = new NPC(jmeno, id, dialog);
                room.addCharacter(npc);
            }
        }
    }

    private void setStartingRoom(World world, JsonObject json) {
        String startId = getRequiredString(json, "start");
        Room startRoom = world.getRoom(startId);

        if (startRoom == null) {
            throw new RuntimeException("Start místnost '" + startId + "' neexistuje (kontroluj JSON 'id').");
        }

        world.setStartingRoom(startRoom);
    }
    private String getRequiredString(JsonObject obj, String key) {
        if (!obj.has(key) || obj.get(key).isJsonNull()) {
            throw new RuntimeException("V JSON chybí povinný klíč '" + key + "'.");
        }
        return obj.get(key).getAsString();
    }
}
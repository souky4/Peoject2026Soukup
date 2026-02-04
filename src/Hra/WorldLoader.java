package Hra;

import Hrac.Character;
import Hrac.Item;
import Hrac.NPC;
import Hrac.Room;
import com.google.gson.*;
import java.io.*;

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
        JsonArray roomsJson = json.getAsJsonArray("mistnosti");

        for (JsonElement element : roomsJson) {
            JsonObject roomJson = element.getAsJsonObject();
            String id = roomJson.get("id").getAsString();
            String nazev = roomJson.get("nazev").getAsString();
            String popis = roomJson.get("popis").getAsString();

            Room room = new Room(id,nazev,popis);
            world.addRoom(room);
        }
    }


    private void connectRooms(World world, JsonObject json) {
        JsonArray roomsJson = json.getAsJsonArray("mistnosti");

        for (JsonElement element : roomsJson) {
            JsonObject roomJson = element.getAsJsonObject();
            String roomId = roomJson.get("id").getAsString();

            Room room = world.getRoom(roomId);
            JsonObject exitsJson = roomJson.getAsJsonObject("exits");
            for (String smer : exitsJson.keySet()) {
                String targetId = exitsJson.get(smer).getAsString();
                Room targeRoom = world.getRoom(targetId);
                room.addExit(smer,targeRoom);
            }
        }
    }

    private void loadItems(World world, JsonObject json) {
        JsonArray roomsJson = json.getAsJsonArray("rooms");

        for (JsonElement element : roomsJson) {
            JsonObject roomJson = element.getAsJsonObject();
            Room room = world.getRoom(roomJson.get("id").getAsString());
            JsonArray itemsJson = roomJson.getAsJsonArray("items");

            if (itemsJson == null) continue;

            for (JsonElement itemElement : itemsJson) {
                JsonObject itemJson = itemElement.getAsJsonObject();
                Item item = new Item(
                        itemJson.get("name").getAsString(),
                        itemJson.get("description").getAsString(),
                        itemJson.get("portable").getAsBoolean()
                );
                room.addItem(item);
            }
        }
    }

    private void loadCharacters(World world, JsonObject json) {
        JsonArray roomsJson = json.getAsJsonArray("mistnosti");

        for (JsonElement element : roomsJson) {
            JsonObject roomJson = element.getAsJsonObject();
            Room room = world.getRoom(roomJson.get("id").getAsString());
            JsonArray charsJson = roomJson.getAsJsonArray("characters");

            if (charsJson == null ) continue;

            for (JsonElement charElement : charsJson) {
                JsonObject charJson = charElement.getAsJsonObject();
                Character npc = new NPC(
                        charJson.get("id").getAsString(),
                        charJson.get("jmeno").getAsString(),
                        charJson.get("dialog").getAsString()
                );
            }
        }
    }

    private void setStartingRoom(World world, JsonObject json) {
        String startId = json.get("start").getAsString();
        world.setStartingRoom(world.getRoom(startId));
    }
}
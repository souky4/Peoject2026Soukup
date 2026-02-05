package Hra;

import Commands.*;

import java.util.HashMap;
import java.util.Map;

public class SpravcePrikazu {
    private Map<String, Command> commands ;

   public SpravcePrikazu(){
       commands = new HashMap<>();

       commands.put("poloz",new DropCommand());
       commands.put("konec",new EndCommand()) ;
       commands.put("prozkoumat",new ExploreCommand());
       commands.put("jid",new GoCommand());
       commands.put("pomoc", new HelpCommand());
       commands.put("napoveda", new HelpCommand());
       commands.put("seber",new TakeCommand());
       commands.put("promluv",new TalkCommand());
       commands.put("pouzij",new UseCommand());
   }

   public void provedPrikaz(String prikaz, Game game) {
       if (prikaz == null || prikaz.trim().isEmpty()) {
           game.printMessage("Nezadal jsi žádný příkaz.");
           return;
       }

       String trimmed = prikaz.trim();
       String[] parts = trimmed.split("\\s+");
       String commandName = parts[0].toLowerCase();

       // argument = zbytek řádku (umožní i více slov)
       String argument = null;
       if (parts.length > 1) {
           argument = trimmed.substring(parts[0].length()).trim();
           if (argument.isEmpty()) argument = null;
       }

       Command cmd = commands.get(commandName);
       if (cmd == null) {
           game.printMessage("Neznámý příkaz. Napiš 'pomoc'.");
           return;
       }

       cmd.proved(game, argument);



   }

}

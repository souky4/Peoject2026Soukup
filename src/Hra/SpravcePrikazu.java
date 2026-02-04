package Hra;

import Commands.*;

import java.util.HashMap;
import java.util.Map;

public class SpravcePrikazu {
    private Map<String, Command> commands ;

   public SpravcePrikazu(){
       commands = new HashMap<>();

       commands.put("Polož",new DropCommand());
       commands.put("Konec",new EndCommand()) ;
       commands.put("Prozkoumat",new ExploreCommand());
       commands.put("Jdi",new GoCommand());
       commands.put("Pomoc", new HelpCommand());
       commands.put("Napoveda", new HelpCommand());
       commands.put("Seber",new TakeCommand());
       commands.put("Promluv",new TalkCommand());
       commands.put("Pouzij",new UseCommand());
   }

   public void provedPrikaz(String prikaz){


   }




}

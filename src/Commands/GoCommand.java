package Commands;
import Hra.Game;

public class GoCommand implements Command {


    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("Kam mám jít? (např. jdi sever)");
            return;
        }
        game.moveTo(argument.toLowerCase());

    }
}

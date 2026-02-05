package Commands;
import Hra.Game;

public class UseCommand implements Command {


    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("Co mám použít? (např. pouzij klic)");
            return;
        }
        game.useItem(argument.toLowerCase());

    }
}

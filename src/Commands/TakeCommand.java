package Commands;
import Hra.Game;
import Hrac.Item;

public class TakeCommand implements Command {



    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("Co mám sebrat?");
            return;
        }

        game.takeItem(argument);

    }
}

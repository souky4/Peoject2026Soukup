package Commands;
import Hra.Game;

public class HelpCommand implements Command {


    @Override
    public void proved(Game game, String argument) {
        game.showHelp();

    }
}

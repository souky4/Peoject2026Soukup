package Commands;
import Hra.Game;

public class EndCommand implements Command {


    @Override
    public void proved(Game game, String argument) {
        game.endGame();

    }
}

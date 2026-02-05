package Commands;
import Hra.Game;

public class ExploreCommand implements Command {


    @Override
    public void proved(Game game, String argument) {
        game.showCurrentRoom();

    }
}

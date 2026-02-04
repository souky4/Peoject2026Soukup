package Commands;
import Hra.Game;

public class DropCommand implements Command {


    @Override
    public void proved(Game game, String argument) {
        if(argument == null){
            game.printMessage("Co mám položit");
            return;
        }

    }
}

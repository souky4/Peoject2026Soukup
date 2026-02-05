package Commands;
import Hra.Game;

public class TalkCommand implements Command {


    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("S kým mám mluvit? (např. promluv knihovnik)");
            return;
        }
        game.talkTo(argument.toLowerCase());

    }
}

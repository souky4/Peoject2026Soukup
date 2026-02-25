package Commands;
import Hra.Game;
/**
 * Příkaz pro mluveni s NPC.
 *
 * @author Ondrej Soukup
 */
public class TalkCommand implements Command {

    /**
     * promluvi s NPC
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("S kým mám mluvit? (např. promluv knihovnik)");
            return;
        }
        game.talkTo(argument.toLowerCase());

    }
}

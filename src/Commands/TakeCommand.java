package Commands;
import Hra.Game;

/**
 * Příkaz pro sebrani itemu.
 *
 * @author Ondrej Soukup
 */
public class TakeCommand implements Command {

    /**
     * sebere item
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("Co mám sebrat? (např. seber mec)");
            return;
        }
        game.takeItem(argument.toLowerCase());

    }
}

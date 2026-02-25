package Commands;
import Hra.Game;

/**
 * Příkaz pro pouziti predmetu.
 *
 * @author Ondrej Soukup
 */

public class UseCommand implements Command {
    /**
     * pouzije predmet
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("Co mám použít? (např. pouzij klic)");
            return;
        }
        game.useItem(argument.toLowerCase());

    }
}

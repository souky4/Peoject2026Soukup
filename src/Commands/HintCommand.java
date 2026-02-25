package Commands;
import Hra.Game;

/**
 * Příkaz pro napovedu uzivateli.
 *
 * @author Ondrej Soukup
 */

public class HintCommand implements Command {

    /**
     * vypise nepovedu
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        game.showHint();

    }
}

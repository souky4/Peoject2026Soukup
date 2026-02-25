package Commands;
import Hra.Game;
/**
 * Příkaz pro prozkoumani mistnosti.
 *
 * @author Ondrej Soukup
 */

public class ExploreCommand implements Command {

    /**
     * Prozkouma danou mistnost
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        game.showCurrentRoom();

    }
}

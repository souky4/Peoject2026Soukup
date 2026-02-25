package Commands;
import Hra.Game;
/**
 * Příkaz pro ukonceni hry.
 *
 * @author Ondrej Soukup
 */

public class EndCommand implements Command {
    /**
     * Ukonci hru
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        game.endGame();

    }
}

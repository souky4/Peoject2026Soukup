package Commands;
import Hra.Game;
/**
 * Příkaz pro pomoc uzivateli.
 *
 * @author Ondrej Soukup
 */

public class HelpCommand implements Command {

    /**
     * provede pomoc
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        game.showHelp();

    }
}

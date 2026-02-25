package Commands;
import Hra.Game;
/**
 * Příkaz pro polozeni predmetu.
 *
 * @author Ondrej Soukup
 */

public class DropCommand implements Command {

    /**
     * provede polozeni predmetu
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("Co mám položit? (např. poloz mec)");
            return;
        }
        game.dropItem(argument.toLowerCase());

    }
}

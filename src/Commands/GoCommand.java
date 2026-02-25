package Commands;
import Hra.Game;
/**
 * Příkaz pro pohyb hráče.
 *
 * @author Ondrej Soukup
 */

public class GoCommand implements Command {
    /**
     * provede pohyb hrace
     *
     * @param game hra
     * @param argument argument prikazu
     */
    @Override
    public void proved(Game game, String argument) {
        if (argument == null) {
            game.printMessage("Kam mám jít? (např. jdi sever)");
            return;
        }
        game.moveTo(argument.toLowerCase());

    }
}

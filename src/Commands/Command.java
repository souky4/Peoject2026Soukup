package Commands;

import Hra.Game;
/**
 * Rozhraní pro herní příkazy.
 * Každý příkaz implementuje metodu proved.
 *
 * @author Ondrej Soukup
 */


public interface Command {
   /**
    * provede dany prikaz
    *
    * @param game hra
    * @param argument argument prikazu
    */
   void proved(Game game,String argument);
}

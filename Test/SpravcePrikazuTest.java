import Hra.Game;
import Hra.SpravcePrikazu;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit testy pro správce příkazů.
 *
 * @author Ondrej Soukup
 */
class SpravcePrikazuTest {

    @Test
    void provedPrikaz_EmptyInputVypiseChybu() {
        TestGame game = new TestGame();
        SpravcePrikazu spravce = new SpravcePrikazu();

        spravce.provedPrikaz("   ", game);

        assertTrue(game.messages.stream().anyMatch(m -> m.contains("Nezadal jsi žádný příkaz")));
    }

    @Test
    void provedPrikaz_NeznamyPrikazVypiseNapovedu() {
        TestGame game = new TestGame();
        SpravcePrikazu spravce = new SpravcePrikazu();

        spravce.provedPrikaz("neco", game);

        assertTrue(game.messages.stream().anyMatch(m -> m.contains("Neznámý příkaz")));
    }

    static class TestGame extends Game {
        final List<String> messages = new ArrayList<>();

        @Override
        public void printMessage(String text) {
            messages.add(text);
        }
    }
}
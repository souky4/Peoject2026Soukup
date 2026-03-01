import Hra.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit testy základního chování třídy Game bez zásahů do produkčních tříd.
 *
 * @author Ondrej Soukup
 */
class GameBehaviorTest {

    @Test
    void takeItem_NeznamyPredmetVypiseHlasku() {
        TestGame game = new TestGame();

        game.takeItem("neexistuje");

        assertTrue(game.messages.stream().anyMatch(m -> m.contains("Takový předmět tu není")));
    }

    @Test
    void moveTo_SpatnySmerVypiseHlasku() {
        TestGame game = new TestGame();

        game.moveTo("nahoru");

        assertTrue(game.messages.stream().anyMatch(m -> m.contains("Tímto směrem se jít nedá")));
    }

    @Test
    void talkTo_NeznameNpcVypiseHlasku() {
        TestGame game = new TestGame();

        game.talkTo("nikdo");

        assertTrue(game.messages.stream().anyMatch(m -> m.contains("Nikdo takový tu není")));
    }

    @Test
    void useItem_PredmetMimoInventarVypiseHlasku() {
        TestGame game = new TestGame();

        game.useItem("klic");

        assertTrue(game.messages.stream().anyMatch(m -> m.contains("Takový předmět nemáš v inventáři")));
    }

    static class TestGame extends Game {
        final List<String> messages = new ArrayList<>();

        @Override
        public void printMessage(String text) {
            messages.add(text);
        }
    }
}
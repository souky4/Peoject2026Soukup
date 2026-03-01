import Hrac.Inventory;
import Hrac.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testy pro třídu Inventory.
 *
 * @author Ondrej Soukup
 */
class InventoryTest {

    @Test
    void addItem_PridaPredmetPokudJeMisto() {
        Inventory inventory = new Inventory(2);
        Item mec = new Item("Meč", "mec", true, "Test");

        boolean added = inventory.addItem(mec);

        assertTrue(added);
        assertSame(mec, inventory.getItem("mec"));
    }

    @Test
    void addItem_VratiFalsePokudJeInventarPlny() {
        Inventory inventory = new Inventory(1);
        inventory.addItem(new Item("Meč", "mec", true, "Test"));

        boolean added = inventory.addItem(new Item("Štít", "stit", true, "Test"));

        assertFalse(added);
        assertNull(inventory.getItem("stit"));
    }

    @Test
    void getItem_VratiNullPokudPredmetNeexistuje() {
        Inventory inventory = new Inventory(2);

        assertNull(inventory.getItem("neexistuje"));
    }
}

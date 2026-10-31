import org.junit.jupiter.api.Test;
import project.InventoryService;
import project.OrderProcessor;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderProcessorIntegrationTest {

    public static class InMemoryInventoryService implements InventoryService {
        public final HashMap<Integer, Integer> inventory = new HashMap<>();

        @Override
        public boolean checkStock(int itemId, int quantity) {
            return inventory.getOrDefault(itemId, 0) >= quantity;
        }

        @Override
        public boolean reserveItem(int itemId, int quantity) {
            if (this.checkStock(itemId, quantity)) {
                int currentQuantity = inventory.getOrDefault(itemId, 0);
                inventory.put(itemId, currentQuantity - quantity);
                return true;
            }
            return false;
        }
    }

    @Test
    public void testOrderProcessorWithRealInventory() {
        InMemoryInventoryService inventoryService = new InMemoryInventoryService();
        inventoryService.inventory.put(1, 5);
        OrderProcessor orderProcessor = new OrderProcessor(inventoryService);
        //Ordering 2 of itemID: 1 from an available stock of 5 should reduce available stock to 3
        boolean result = orderProcessor.processOrder(1, 2);
        assertEquals(3, inventoryService.inventory.get(1));
    }
}

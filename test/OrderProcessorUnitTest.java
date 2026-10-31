import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.InventoryService;
import project.OrderProcessor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderProcessorUnitTest {
    @Test
    public void testProcessOrder_InStock() {
        InventoryService inventoryService = Mockito.mock(InventoryService.class);
        OrderProcessor orderProcessor = new OrderProcessor(inventoryService);
        Mockito.when(inventoryService.checkStock(1,1)).thenReturn(true);
        //processOrder() relies on return of reserveItem(), so the mock must report a successful reservation
        Mockito.when(inventoryService.reserveItem(1,1)).thenReturn(true);
        assertTrue(orderProcessor.processOrder(1, 1));
    }

    @Test
    public void testProcessOrder_OutOfStock() {
        InventoryService inventoryService = Mockito.mock(InventoryService.class);
        OrderProcessor orderProcessor = new OrderProcessor(inventoryService);
        Mockito.when(inventoryService.checkStock(1,1)).thenReturn(false);
        assertFalse(orderProcessor.processOrder(1, 1));
    }
}

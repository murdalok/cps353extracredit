package project;

public class OrderProcessor {
    private final InventoryService inventoryService;

    public OrderProcessor(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public boolean processOrder(int itemId, int quantity) {
        if (this.inventoryService.checkStock(itemId, quantity)) {
            return inventoryService.reserveItem(itemId, quantity);
        }
        return false;
    }
}

package project;

public interface InventoryService {
    boolean checkStock(int itemId, int quantity);
    boolean reserveItem(int itemId, int quantity);
}

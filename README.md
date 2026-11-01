# Extra Credit Assignment

## Components

**`InventoryService`** (interface)
Has two methods:
- `checkStock(itemId, quantity)` returns `true` if enough units are in stock.
- `reserveItem(itemId, quantity)` returns `true` if the units were reserved.

**`OrderProcessor`** (class)
Client that receives an `InventoryService` through its constructor.
Has one method:
- `processOrder(itemId, quantity)` Checks stock using `checkStock()`.
If there is enough, it reserves the item using `reserveItem()`  and returns the result of the reservation.
If not, returns `false`.

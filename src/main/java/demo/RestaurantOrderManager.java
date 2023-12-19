package demo;

public class RestaurantOrderManager implements OrderManager {
    private Order[] orders;
    public RestaurantOrderManager() {
        orders = new Order[10];
    }
    public Order[] getOrders() {
        return orders;
    }
    public float getTotalOrdersPrice() {
        float cost = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                cost += orders[i].getTotalPrice();
            }
        }
        return cost;
    }

    public int getTotalItemOrders(String itemName) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                count += orders[i].getTotalItemCount(itemName);
            }
        }
        return count;
    }

    public void addOrder(int tableNumber, Order order) {
        if (tableNumber < 0 || tableNumber >= orders.length) {
            return;
        }
        if (orders[tableNumber] != null) {
            return;
        }
        orders[tableNumber] = order;
    }
    public boolean remove(int tableNumber) {
        if (orders[tableNumber] != null) {
            orders[tableNumber] = null;
            return true;
        }
        return false;
    }
    public int ordersCount() {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                count++;
            }
        }
        return count;
    }
    public int freeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                return i;
            }
        }
        return -1;
    }
    public void addItem(Item item, int tableNumber) {
        orders[tableNumber].add(item);
    }
    public int[] freeTableNumbers() {
        int[] freeTables = new int[orders.length];
        int freeTablesCount = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                freeTables[freeTablesCount] = i;
                freeTablesCount++;
            }
        }
        int[] result = new int[freeTablesCount];
        System.arraycopy(freeTables, 0, result, 0, freeTablesCount);
        return result;
    }
    public Order getOrder(int tableNumber) {
        return orders[tableNumber];
    }
    public void remove(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == order) {
                orders[i] = null;
            }
        }
    }
    public int removeAll(Order order) {
        int removed = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == order) {
                orders[i] = null;
                removed++;
            }
        }
        return removed;
    }
}
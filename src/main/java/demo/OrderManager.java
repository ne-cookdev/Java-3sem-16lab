package demo;

public interface OrderManager {
    Order[] getOrders();

    float getTotalOrdersPrice();

    int getTotalItemOrders(String name);
}

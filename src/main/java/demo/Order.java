package demo;

public interface Order {
    boolean add(Item item);

    boolean remove(String name);

    int removeAll(String name);

    int getOrderCount();

    float getTotalPrice();

    int getTotalItemCount(String name);

    String[] getUniqueOrderNames();

    Item[] getItems();

    String showItems();

    Item[] getItemsSorted();
}
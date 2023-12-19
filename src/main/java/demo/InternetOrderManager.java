package demo;

import java.util.HashMap;

public class InternetOrderManager implements OrderManager{
    private final HashMap<Customer, Order> address_order;

    InternetOrderManager() {
        this.address_order = new HashMap<Customer, Order>();
    }

    public boolean addOrder(Customer customer, Order order) throws Exception {
        if (address_order.containsKey(customer))
            throw new Exception("OrderAlreadyAddedException");
        else {
            address_order.put(customer, order);
            return true;
        }
    }

    public Order getOrder(Customer customer) {
        return address_order.getOrDefault(customer, null);
    }

    public boolean removeOrder(Customer customer) {
        try {
            if (!address_order.containsKey(customer)) {
                return false;
            }

            address_order.remove(customer);
            return true;
        } catch (Exception exc) {
            System.out.println("E:" + exc.getMessage());
            return false;
        }
    }

    public boolean addAddressItem(Customer customer, Item item) {
        Order order = address_order.getOrDefault(customer, null);
        if (order == null)
            return false;
        return order.add(item);
    }

    public Order[] getInternetOrders() {
        int InternetOrdersSize = 0, index = 0;
        Order[] order = new Order[address_order.size()];

        for (Customer cstmr : address_order.keySet()) {
            order[index] = address_order.get(cstmr);

            if (order[index] instanceof InternetOrder) {
                InternetOrdersSize++;
            }

            index++;
        }

        index = 0;
        Order[] orders = new Order[InternetOrdersSize];

        for (int i = 0; i < address_order.size(); i++) {
            if (order[i] instanceof InternetOrder) {
                orders[index++] = order[i];
            }
        }

        return orders;
    }

    public int InternetOrdersAmount(){
        return address_order.size();
    }

    public Order[] getOrders() {
        int index = 0;
        Order[] orders = new Order[address_order.size()];
        for (Customer cstmr : address_order.keySet())
            orders[index++] = address_order.get(cstmr);
        return orders;
    }

    public float getTotalOrdersPrice() {
        float sum = 0;
        Order[] orders = getInternetOrders();

        for (Order order : orders) {
            sum += order.getTotalPrice();
        }

        return sum;
    }

    public int getTotalItemOrders(String name) {
        int count = 0;
        Order[] orders = getOrders();

        for (Order order : orders) {
            count += order.getTotalItemCount(name);
        }

        return count;
    }
}
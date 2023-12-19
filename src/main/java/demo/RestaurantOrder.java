package demo;

public class RestaurantOrder implements Order {
    private int order_count = 0;
    private ListNode head;
    private ListNode tail;

    public boolean add(Item item) {
        order_count++;
        ListNode new_order = new ListNode(item);

        if (head == null) {
            head = new_order;
        } else {
            ListNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = new_order;
            new_order.previous = current;
        }

        tail = new_order;
        return true;
    }

    public boolean remove(String name) {
        if (head == null || order_count < 0) {
            return false;
        }
        order_count--;

        ListNode current = tail, temp;

        while (!name.equals(current.item.getName())) {
            if (current.previous == null) {
                return false;
            }
            current = current.previous;
        }

        if (current.next == null) {
            if (current == head) {
                head = null;
                tail = null;
                return true;
            }

            current.previous.next = null;
            tail = current.previous;
            return true;
        }

        if (current.previous == null) {
            head = current.next;
            current.next.previous = null;
            current.next = null;
            return true;
        }

        temp = current.previous;
        current.previous.next = current.next;
        current.next.previous = temp;
        current.next = null;
        current.previous = null;
        return true;
    }

    public int removeAll(String name) {
        int cnt = 0;

        for (ListNode current = tail; current != null; current = current.previous) {
            if (name.equals(current.item.getName())) {
                remove(name);
                cnt++;
            }
        }

        return cnt;
    }

    public int getOrderCount() {
        return order_count;
    }

    public Item[] getItems() {
        try {
            int index = 0;
            Item[] items = new Item[order_count];

            for (ListNode current = head; current != null && index < order_count; current = current.next) {
                items[index++] = current.item;
            }

            return items;
        } catch (NegativeArraySizeException exc) {
            System.out.println("Input Error: " + exc.getMessage());
            System.exit(1);
        }

        return null;
    }

    @Override
    public String showItems() {
        return null;
    }

    public float getTotalPrice() {
        float sum = 0;
        for (Item index : getItems()) {
            sum += index.getPrice();
        }
        return sum;
    }

    public int getTotalItemCount(String name) {
        int count = 0;
        for (ListNode current = head; current != null; current = current.next) {
            if (name.equals(current.item.getName())) {
                count++;
            }
        }
        return count;
    }

    public String[] getUniqueOrderNames() {
        Item[] items = getItems();
        int index = 0;

        for (ListNode current = head; current != null; current = current.next) {
            boolean flag_names_are_unique = true;
            for (int i = 0; i < index && flag_names_are_unique; i++) {
                if (current.item.getName().equals(items[i].getName())) {
                    flag_names_are_unique = false;
                }
            }
            if (flag_names_are_unique) {
                items[index++] = current.item;
            }
        }

        String[] total = new String[index];
        for (int i = 0; i < total.length; i++) {
            total[i] = items[i].getName();
        }

        return total;
    }

    public Item[] getItemsSorted() {
        Item[] items = getItems();
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < i; j++) {
                if (items[i].getPrice() > items[j].getPrice()) {
                    Item temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                }
            }
        }
        return items;
    }
}
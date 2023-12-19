package demo;

public class Main {
    public static void main(String[] args) {
        try {
            String firstName = "Ivan";
            String secondname = "Ivanov";
            String cityName = "Moscow";
            String streetName = "Vernadskogo";
            int buildingNumber = 1;
            int apartmentNumber = 1;
            int zipCode = 123456;
            char buildingLetter = 'A';
            int age = 20;
            Address address = new Address(cityName, streetName, buildingNumber, apartmentNumber, zipCode, buildingLetter);
            Customer customer = new Customer(firstName, secondname, address, age);
            InternetOrderManager orderManager = new InternetOrderManager();

            RestaurantOrder rest = new RestaurantOrder();
            System.out.println("\nRestaurant Order:\n");

            System.out.println("  Item Added: " + rest.add(new Dish(DishType.SPAGHETTI, 3)));
            System.out.println("  Item Added: " + rest.add(new Drink(DrinkType.SODA, 2)));
            System.out.println("  Item Added: " + rest.add(new Dish(DishType.KHARCHO, 7)));
            System.out.println("  Item Added: " + rest.add(new Drink(DrinkType.TEQUILA, 12)));
            System.out.println("  Item Added: " + rest.add(new Drink(DrinkType.TEQUILA, 7)));

            System.out.println("\n  Amount of items: " + rest.getOrderCount());
            System.out.println("  Item `Soda` removed: " + rest.remove("Soda"));
            System.out.println("  Amount of removed `Tequila`s: " + rest.removeAll("Tequila"));
            System.out.println("  Amount of items after removal: " + rest.getOrderCount());

            for (Item item : rest.getItems()) {
                System.out.println("    Item Name: " + item.getName());
            }

            System.out.println("\n  Total Price: " + rest.getTotalPrice());
            System.out.println("  Item Added: " + rest.add(new Dish(DishType.SPAGHETTI, 25)));
            System.out.println("  Amount of `Spagetti`s: " + rest.getTotalItemCount(DishType.SPAGHETTI.getName()));

            System.out.println("\n  Current Items:");
            for (Item item : rest.getItems()) {
                System.out.println("    Item Name: " + item.getName());
            }

            System.out.println("\n  Unique Items:");
            for (int i = 0; i < rest.getUniqueOrderNames().length; i++) {
                System.out.println("    " + rest.getUniqueOrderNames()[i]);
            }

            System.out.println("\n  Items, Sorted by Price");
            for (int i = 0; i < rest.getItemsSorted().length; i++) {
                String name = rest.getItemsSorted()[i].getName();
                float price = rest.getItemsSorted()[i].getPrice();
                System.out.println("    Item name: `" + name + "` and Price: $" + price);
            }
            System.out.println("\n  Order added: " + orderManager.addOrder(customer, rest));

            InternetOrder internet = new InternetOrder();
            System.out.println("\nInternet Order:\n");
            System.out.println("  Item Added: " + internet.add(new Dish(DishType.PIZZA, 4)));
            System.out.println("  Item Added: " + internet.add(new Dish(DishType.SUSHIROLLS, 6)));
            System.out.println("  Order Added: " + orderManager.addOrder(customer, internet));
            System.out.println("  Total Price: $" + orderManager.getTotalOrdersPrice());
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
}
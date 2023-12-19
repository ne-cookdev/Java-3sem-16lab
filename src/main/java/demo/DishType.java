package demo;

public enum DishType {
    SPAGHETTI("Spaghetti", "Pasta with round cross-section"),
    KHARCHO("Kharcho", "Georgian soup with beef and rice"),
    FRIEDRIBS("Fried Ribs", "Delicious and hearty dish with sauce"),
    KAMCHATKACRABMEAT("Kamchatka Crab Meat", "Tender and soft meat from Kamchatka crab"),
    FISHANDCHIPS("Fish and Chips", "Delicious homemade dish with fish and potato chips"),
    SHRIMPTEMPURA("Shrimp Tempura", "Hearty shrimp in crispy batter"),
    SUSHIROLLS("Sushi Rolls", "Dish made of rice, vinegar, and various seafood"),
    PIZZA("Pizza", "Italian dish with toppings on top"),
    CHEESEBURGER("Cheeseburger", "Hamburger with cheese"),
    SHWARMA("Shwarma", "Delicious Goodness");

    private final String name;
    private final String description;

    DishType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

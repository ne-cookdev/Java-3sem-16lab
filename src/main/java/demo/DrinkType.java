package demo;

public enum DrinkType {
    BEER("Beer", "Fermented alcoholic beverage made from malted barley"),
    WINE("Wine", "Alcoholic beverage made from fermented grapes or other fruits"),
    VODKA("Vodka", "Clear distilled alcoholic beverage"),
    BRANDY("Brandy", "Distilled wine or fermented fruit juice"),
    CHAMPAGNE("Champagne", "Sparkling wine typically associated with celebrations"),
    WHISKEY("Whiskey", "Distilled alcoholic beverage made from fermented grain mash"),
    TEQUILA("Tequila", "Distilled alcoholic beverage made from the blue agave plant"),
    RUM("Rum", "Distilled alcoholic beverage made from sugarcane byproducts"),
    VERMUTH("Vermuth", "A fortified wine flavored with various botanicals"),
    LIQUOR("Liquor", "Alcoholic beverage made by distillation of grains, fruit, or vegetables"),
    JAGERMEISTER("Jagermeister", "Herbal liqueur made with 56 different herbs, fruits, roots, and spices"),
    JUICE("Juice", "Liquid extracted from fruits or vegetables"),
    COFFEE("Coffee", "Beverage made from brewed coffee beans"),
    GREENTEA("Green Tea", "Tea made from Camellia sinensis leaves without oxidation"),
    BLACKTEA("Black Tea", "Tea made from fully oxidized Camellia sinensis leaves"),
    MILK("Milk", "White liquid produced by the mammary glands of mammals"),
    WATER("Water", "Clear, colorless, odorless, and tasteless liquid essential for life"),
    SODA("Soda", "Carbonated beverage with various flavors");

    private final String name;
    private final String description;

    DrinkType(String name, String description) {
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

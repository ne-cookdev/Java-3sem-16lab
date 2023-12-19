package demo;

final class Drink implements Item {
    private final DrinkType type;
    private final float price;

    Drink(DrinkType type) throws IllegalArgumentException {
        this.type = type;
        price = 0;
    }

    Drink(DrinkType type, float price) throws IllegalArgumentException {
        this.type = type;
        this.price = price;
        if (price < 0)
            throw new IllegalArgumentException("Incorrect price: " + price);
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getDescription() {
        return type.getDescription();
    }

    @Override
    public float getPrice() {
        return price;
    }
}
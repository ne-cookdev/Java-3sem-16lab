package demo;

final class Dish implements Item {
    private final DishType type;
    private final float price;

    Dish(DishType type) {
        this.type = type;
        price = 0;
    }

    Dish(DishType type, float price) throws IllegalArgumentException {
        this.type = type;
        this.price = price;
        if (price < 0) {
            throw new IllegalArgumentException("Incorrect price: " + price);
        }
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
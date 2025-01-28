package domain.food;

public class Food {
    protected int number;
    protected String name;
    protected int price;

    public Food(int number, String name, int price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

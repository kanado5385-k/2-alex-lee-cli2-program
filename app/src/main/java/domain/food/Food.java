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

    public boolean isSameNumber(int number) {
        return this.number == number;
    }

    public int getTotalPrice(int inputQuantity) {
        return price * inputQuantity;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

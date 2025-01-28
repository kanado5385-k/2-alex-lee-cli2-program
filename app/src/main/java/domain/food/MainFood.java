package domain.food;

public class MainFood extends Food {
    protected int gram;

    public MainFood(int number, String name, int price, int gram) {
        super(number, name, price);
        this.gram = gram;
    }
}

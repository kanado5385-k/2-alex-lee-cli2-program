package domain.food;

public class MainFood extends Food {
    protected int gram;

    public MainFood(int number, String name, int price, int gram, int quantity) {
        super(number, name, price, quantity);
        this.gram = gram;
    }

    public int getGram() {
        return gram;
    }
}

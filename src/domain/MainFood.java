package domain;

public class MainFood extends Food {
    public int gram;

    public MainFood(String name, int price, int gram) {
        super(name, price);
        this.gram = gram;
    }
}

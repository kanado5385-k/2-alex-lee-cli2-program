package domain.food;

public class Drink extends SideFood {
    protected int ml;

    public Drink(String name, int price, int ml) {
        super(name, price);
        this.ml = ml;
    }
}

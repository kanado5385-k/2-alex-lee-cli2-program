package domain.food;

public class Drink extends SideFood {
    protected int ml;

    public Drink(int number, String name, int price, int ml) {
        super(number, name, price);
        this.ml = ml;
    }

    public int getMl() {
        return ml;
    }
}

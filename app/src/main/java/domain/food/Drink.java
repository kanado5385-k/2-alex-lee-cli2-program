package domain.food;

public class Drink extends SideFood {
    protected int ml;

    public Drink(int number, String name, int price, int ml, int quantity) {
        super(number, name, price, quantity);
        this.ml = ml;
    }

    public int getMl() {
        return ml;
    }
}

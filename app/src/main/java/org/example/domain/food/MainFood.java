package org.example.domain.food;

public class MainFood extends Food {
    protected int gram;

    public MainFood(String name, int price, int gram) {
        super(name, price);
        this.gram = gram;
    }
}

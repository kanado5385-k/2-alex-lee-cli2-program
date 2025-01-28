package domain.menu;

import domain.food.Drink;
import domain.food.SideFood;

import java.util.List;

public class DrinkMenu {
    private final List<Drink> drinks;

    public DrinkMenu(List<Drink> drinks) {
        this.drinks = drinks;
    }
}

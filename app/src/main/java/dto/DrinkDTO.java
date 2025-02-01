package dto;

import domain.food.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkDTO {
    private final int number;
    private final String name;
    private final int price;
    private final int ml;

    private DrinkDTO(Drink drink) {
        this.number = drink.getNumber();
        this.name = drink.getName();
        this.price = drink.getPrice();
        this.ml = drink.getMl();
    }

    public static List<DrinkDTO> getList(List<Drink> drinkList) {
        List<DrinkDTO> list = new ArrayList<>();
        for (Drink item : drinkList) {
            list.add(new DrinkDTO(item));
        }
        return list;
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

    public int getMl() {
        return ml;
    }
}

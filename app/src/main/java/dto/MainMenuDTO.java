package dto;

import domain.food.MainFood;

import java.util.ArrayList;
import java.util.List;

public class MainMenuDTO {
    private final int number;
    private final String name;
    private final int price;
    private final int gram;

    private MainMenuDTO(MainFood mainFood) {
        this.number = mainFood.getNumber();
        this.name = mainFood.getName();
        this.price = mainFood.getPrice();
        this.gram = mainFood.getGram();
    }

    public static List<MainMenuDTO> getList(List<MainFood> mainFoodList) {
        List<MainMenuDTO> list = new ArrayList<>();
        for (MainFood item : mainFoodList) {
            list.add(new MainMenuDTO(item));
        }
        return list;
    }

    public int getGram() {
        return gram;
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

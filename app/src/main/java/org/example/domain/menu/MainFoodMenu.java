package org.example.domain.menu;



import org.example.domain.food.MainFood;

import java.util.List;


public class MainFoodMenu {
    private final List<MainFood> mainFoods;

    public MainFoodMenu(List<MainFood> mainFoods) {
        this.mainFoods = mainFoods;
    }
}

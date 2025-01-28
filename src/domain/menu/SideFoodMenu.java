package domain.menu;

import domain.food.MainFood;
import domain.food.SideFood;

import java.util.List;

public class SideFoodMenu {
    private final List<SideFood> sideFoods;

    public SideFoodMenu(List<SideFood> sideFoods) {
        this.sideFoods = sideFoods;
    }
}

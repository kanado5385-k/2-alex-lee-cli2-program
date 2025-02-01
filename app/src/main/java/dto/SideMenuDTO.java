package dto;

import domain.food.SideFood;

import java.util.ArrayList;
import java.util.List;

public class SideMenuDTO {
    private final int number;
    private final String name;
    private final int price;

    public SideMenuDTO(SideFood sideFood) {
        this.number = sideFood.getNumber();
        this.name = sideFood.getName();
        this.price = sideFood.getPrice();
    }

    public static List<SideMenuDTO> getList(List<SideFood> sideFoodList) {
        List<SideMenuDTO> list = new ArrayList<>();
        for (SideFood item : sideFoodList) {
            list.add(new SideMenuDTO(item));
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
}

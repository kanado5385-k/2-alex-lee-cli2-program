package domain.menu;



import domain.food.Food;
import domain.food.MainFood;
import domain.food.SideFood;
import domain.menu.interfaces.MenuInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utilities.InputNumberValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SideFoodMenu implements MenuInterface {
    private final List<SideFood> sideFoods;

    public SideFoodMenu() throws IOException, ParseException {
        this.sideFoods = readMenuFile();
    }

    public List<SideFood> readMenuFile() throws IOException, ParseException {
        List<SideFood> sideFoods = new ArrayList<>();
        JSONParser parser = new JSONParser();

        // "data/main-menu.json" 경로의 파일을 읽기
        Reader reader = new FileReader("data/side-menu.json");
        JSONArray jsonArray = (JSONArray) parser.parse(reader);

        // JSON 배열을 순회하면서 MainFood 객체 생성
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            int number = ((Long) jsonObject.get("number")).intValue();
            String name = (String) jsonObject.get("name");
            int price = ((Long) jsonObject.get("price")).intValue();
            int quantity = ((Long) jsonObject.get("quantity")).intValue();

            SideFood sideFood = new SideFood(number, name, price, quantity);
            sideFoods.add(sideFood);
        }
        reader.close();
        return sideFoods;
    }

    public void saveMenuFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("data/side-menu.json")
        );
        writer.write("[");
        for (int i = 0; i < sideFoods.size(); i++) {
            SideFood item = sideFoods.get(i);
            writer.write(String.format(
                    "{\"number\": %d, \"name\": \"%s\", \"price\": %d, \"quantity\": %d}",
                    item.getNumber(), item.getName(), item.getPrice(), item.getQuantity()
            ));
            if (i < sideFoods.size() - 1) {
                writer.write(",\n");
            }
        }
        writer.write("]");
        writer.flush();
        writer.close();
    }

    @Override
    public void validateInputNumber(int inputNum) {
        InputNumberValidator.validateNumberOfFood(inputNum, sideFoods.size());
    }

    @Override
    public int getTotalPrice(int inputNumber, int inputQuantity) {
        Food sideFood = null;
        for (Food food : sideFoods) {
            if (food.isSameNumber(inputNumber)) {
                sideFood = food;
            }
        }
        return  sideFood.getTotalPrice(inputQuantity);
    }

    @Override
    public boolean isSufficientQuantity (int inputNumber, int inputQuantity) {
        Food sideFood = null;
        for (Food food : sideFoods) {
            if (food.isSameNumber(inputNumber)) {
                sideFood = food;
            }
        }
        return  sideFood.isSufficientQuantity(inputQuantity);
    }

    @Override
    public void decreaseQuantity (int inputNumber, int inputQuantity) {
        Food sideFood = null;
        for (Food food : sideFoods) {
            if (food.isSameNumber(inputNumber)) {
                sideFood = food;
            }
        }
        sideFood.decreaseQuantity(inputQuantity);
    }

    public List<SideFood> getSideFoods() {
        return sideFoods;
    }
}

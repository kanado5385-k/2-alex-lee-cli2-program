package domain.menu;

import domain.food.Drink;
import domain.food.Food;
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

public class DrinkMenu implements MenuInterface {
    private final List<Drink> drinks;

    public DrinkMenu() throws IOException, ParseException {
        this.drinks = readMenuFile();
    }

    public List<Drink> readMenuFile() throws IOException, ParseException {
        List<Drink> drinks = new ArrayList<>();
        JSONParser parser = new JSONParser();

        // "data/main-menu.json" 경로의 파일을 읽기
        Reader reader = new FileReader("data/drink-menu.json");
        JSONArray jsonArray = (JSONArray) parser.parse(reader);

        // JSON 배열을 순회하면서 MainFood 객체 생성
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            int number = ((Long) jsonObject.get("number")).intValue();
            String name = (String) jsonObject.get("name");
            int price = ((Long) jsonObject.get("price")).intValue();
            int ml = ((Long) jsonObject.get("ml")).intValue();
            int quantity = ((Long) jsonObject.get("quantity")).intValue();

            Drink drink = new Drink(number, name, price,ml, quantity);
            drinks.add(drink);
        }
        reader.close();
        return drinks;
    }

    public void saveMenuFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("data/drink-menu.json")
        );
        writer.write("[");
        for (int i = 0; i < drinks.size(); i++) {
            Drink item = drinks.get(i);
            writer.write(String.format(
                    "{\"number\": %d, \"name\": \"%s\", \"price\": %d, \"ml\": %d, \"quantity\": %d}",
                    item.getNumber(), item.getName(), item.getPrice(),item.getMl(), item.getQuantity()
            ));
            if (i < drinks.size() - 1) {
                writer.write(",\n");
            }
        }
        writer.write("]");
        writer.flush();
        writer.close();
    }

    @Override
    public void validateInputNumber(int inputNum) {
        InputNumberValidator.validateNumberOfFood(inputNum, drinks.size());
    }

    @Override
    public int getTotalPrice(int inputNumber, int inputQuantity) {
        Food drinkFood = null;
        for (Food food : drinks) {
            if (food.isSameNumber(inputNumber)) {
                drinkFood = food;
            }
        }
        return  drinkFood.getTotalPrice(inputQuantity);
    }

    @Override
    public boolean isSufficientQuantity (int inputNumber, int inputQuantity) {
        Food drinkFood = null;
        for (Food food : drinks) {
            if (food.isSameNumber(inputNumber)) {
                drinkFood = food;
            }
        }
        return  drinkFood.isSufficientQuantity(inputQuantity);
    }

    @Override
    public void decreaseQuantity (int inputNumber, int inputQuantity) {
        Food drinkFood = null;
        for (Food food : drinks) {
            if (food.isSameNumber(inputNumber)) {
                drinkFood = food;
            }
        }
        drinkFood.decreaseQuantity(inputQuantity);
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}

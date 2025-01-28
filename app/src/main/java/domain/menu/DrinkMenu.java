package domain.menu;

import domain.food.Drink;
import domain.food.Food;
import domain.food.MainFood;
import domain.menu.interfaces.MenuInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utilities.InputNumberValidator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DrinkMenu implements MenuInterface {
    private final List<Food> drinks;

    public DrinkMenu() throws IOException, ParseException {
        this.drinks = readMenuFile();
    }

    @Override
    public List<Food> readMenuFile () throws IOException, ParseException {
        List<Food> drinks = new ArrayList<>();
        JSONParser parser = new JSONParser();

        // JSON 파일을 읽기
        Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("drink-menu.json")
        );
        JSONArray jsonArray = (JSONArray) parser.parse(reader);

        // JSON 배열을 순회하면서 MainFood 객체 생성
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            int number = ((Long) jsonObject.get("number")).intValue();
            String name = (String) jsonObject.get("name");
            int price = ((Long) jsonObject.get("price")).intValue();
            int ml = ((Long) jsonObject.get("ml")).intValue();

            Drink drink = new Drink(number, name, price, ml);
            drinks.add(drink);
        }
        return drinks;
    }

    @Override
    public void validateInputNumber(int inputNum) {
        InputNumberValidator.validateInputNumber(inputNum, drinks.size());
    }

    @Override
    public int getTotalPrice(int inputNumber, int inputQuantity) {
        Food mainFood = null;
        for (Food food : drinks) {
            if (food.isSameNumber(inputNumber)) {
                mainFood = food;
            }
        }
        return  mainFood.getTotalPrice(inputQuantity);
    }
}

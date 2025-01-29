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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SideFoodMenu implements MenuInterface {
    private final List<SideFood> sideFoods;

    public SideFoodMenu() throws IOException, ParseException {
        this.sideFoods = readMenuFile();
    }

    public List<SideFood> readMenuFile () throws IOException, ParseException {
        List<SideFood> sideFoods = new ArrayList<>();
        JSONParser parser = new JSONParser();

        // JSON 파일을 읽기
        Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("side-menu.json")
        );
        JSONArray jsonArray = (JSONArray) parser.parse(reader);

        // JSON 배열을 순회하면서 MainFood 객체 생성
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            int number = ((Long) jsonObject.get("number")).intValue();
            String name = (String) jsonObject.get("name");
            int price = ((Long) jsonObject.get("price")).intValue();

            SideFood sideFood = new SideFood(number, name, price);
            sideFoods.add(sideFood);
        }
        return sideFoods;
    }

    @Override
    public void validateInputNumber(int inputNum) {
        InputNumberValidator.validateInputNumber(inputNum, sideFoods.size());
    }

    @Override
    public int getTotalPrice(int inputNumber, int inputQuantity) {
        Food mainFood = null;
        for (Food food : sideFoods) {
            if (food.isSameNumber(inputNumber)) {
                mainFood = food;
            }
        }
        return  mainFood.getTotalPrice(inputQuantity);
    }

    public List<SideFood> getSideFoods() {
        return sideFoods;
    }
}

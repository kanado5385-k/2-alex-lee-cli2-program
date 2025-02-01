package domain.menu;



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

public class MainFoodMenu implements MenuInterface {
    private final List<MainFood> mainFoods;

    public MainFoodMenu() throws IOException, ParseException {
        this.mainFoods = readMenuFile();
    }

    public List<MainFood> readMenuFile () throws IOException, ParseException {
        List<MainFood> mainFoods = new ArrayList<>();
        JSONParser parser = new JSONParser();

        // JSON 파일을 읽기
        Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("main-menu.json")
        );
        JSONArray jsonArray = (JSONArray) parser.parse(reader);

        // JSON 배열을 순회하면서 MainFood 객체 생성
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            int number = ((Long) jsonObject.get("number")).intValue();
            String name = (String) jsonObject.get("name");
            int price = ((Long) jsonObject.get("price")).intValue();
            int gram = ((Long) jsonObject.get("gram")).intValue();

            MainFood mainFood = new MainFood(number, name, price, gram);
            //System.out.println(mainFood.getName());
            mainFoods.add(mainFood);
        }
        return  mainFoods;
    }

    @Override
    public void validateInputNumber(int inputNum) {
        InputNumberValidator.validateNumberOfFood(inputNum, mainFoods.size());
    }

    @Override
    public int getTotalPrice(int inputNumber, int inputQuantity) {
        Food mainFood = null;
        for (Food food : mainFoods) {
            if (food.isSameNumber(inputNumber)) {
                mainFood = food;
            }
        }
        return  mainFood.getTotalPrice(inputQuantity);
    }

    public List<MainFood> getMainFoods() {
        return mainFoods;
    }
}

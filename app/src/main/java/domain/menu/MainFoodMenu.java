package domain.menu;



import domain.food.MainFood;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utilities.InputNumberValidator;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;



public class MainFoodMenu {
    public final List<MainFood> mainFoods;

    public MainFoodMenu() throws IOException, ParseException {
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
            String name = (String) jsonObject.get("name");
            int price = ((Long) jsonObject.get("price")).intValue();
            int gram = ((Long) jsonObject.get("gram")).intValue();

            MainFood mainFood = new MainFood(name, price, gram);
            mainFoods.add(mainFood);
        }

        this.mainFoods = mainFoods;
    }

    public void validateInputNumber(int inputNum) {
        InputNumberValidator.validateInputNumber(inputNum, mainFoods.size());
    }

}

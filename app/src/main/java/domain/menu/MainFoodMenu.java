package domain.menu;



import domain.food.Food;
import domain.food.MainFood;
import domain.menu.interfaces.MenuInterface;
import dto.MainMenuDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utilities.InputNumberValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainFoodMenu implements MenuInterface {
    private final List<MainFood> mainFoods;

    public MainFoodMenu() throws IOException, ParseException {
        this.mainFoods = readMenuFile();
    }

    public List<MainFood> readMenuFile() throws IOException, ParseException {
        List<MainFood> mainFoods = new ArrayList<>();
        JSONParser parser = new JSONParser();

        // "data/main-menu.json" 경로의 파일을 읽기
        Reader reader = new FileReader("data/main-menu.json");
        JSONArray jsonArray = (JSONArray) parser.parse(reader);

        // JSON 배열을 순회하면서 MainFood 객체 생성
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            int number = ((Long) jsonObject.get("number")).intValue();
            String name = (String) jsonObject.get("name");
            int price = ((Long) jsonObject.get("price")).intValue();
            int gram = ((Long) jsonObject.get("gram")).intValue();
            int quantity = ((Long) jsonObject.get("quantity")).intValue();

            MainFood mainFood = new MainFood(number, name, price, gram, quantity);
            mainFoods.add(mainFood);
        }
        reader.close();
        return mainFoods;
    }

    public void saveMenuFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("data/main-menu.json")
        );
        writer.write("[");
        for (int i = 0; i < mainFoods.size(); i++) {
            MainFood item = mainFoods.get(i);
            //System.out.println(item.getQuantity()); //여기까지 잘 된다.
            writer.write(String.format(
                    "{\"number\": %d, \"name\": \"%s\", \"price\": %d, \"gram\": %d, \"quantity\": %d}",
                    item.getNumber(), item.getName(), item.getPrice(), item.getGram(), item.getQuantity()
            ));
            if (i < mainFoods.size() - 1) {
                writer.write(",\n");
            }
        }
        writer.write("]");
        writer.flush();
        writer.close();
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

    @Override
    public boolean isSufficientQuantity (int inputNumber, int inputQuantity) {
        Food mainFood = null;
        for (Food food : mainFoods) {
            if (food.isSameNumber(inputNumber)) {
                mainFood = food;
            }
        }
        return  mainFood.isSufficientQuantity(inputQuantity);
    }

    @Override
    public void decreaseQuantity (int inputNumber, int inputQuantity) {
        Food mainFood = null;
        for (Food food : mainFoods) {
            if (food.isSameNumber(inputNumber)) {
                mainFood = food;
            }
        }
        mainFood.decreaseQuantity(inputQuantity);
    }

    public List<MainFood> getMainFoods() {
        return mainFoods;
    }
}

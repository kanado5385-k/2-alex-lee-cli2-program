package domain.menu;

import static org.junit.jupiter.api.Assertions.*;

import domain.food.MainFood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.simple.parser.ParseException;
import java.io.IOException;

class MainFoodMenuTest {
    private MainFoodMenu mainFoodMenu;

    @BeforeEach
    void setUp() throws IOException, ParseException {
        mainFoodMenu = new MainFoodMenu();
    }


    @Test
    void testMultipleFoodsParsing() {
        // mainFoods 리스트의 크기 확인 (JSON 데이터 개수와 일치해야 함)
        assertEquals(2, mainFoodMenu.mainFoods.size(), "JSON에 정의된 음식 개수와 mainFoods의 크기가 일치해야 합니다.");

        // 두 번째 음식 데이터 확인
        MainFood secondFood = mainFoodMenu.mainFoods.get(1);
        assertEquals(12000, secondFood.getPrice(), "두 번째 음식 가격이 12000이어야 합니다.");
    }
}
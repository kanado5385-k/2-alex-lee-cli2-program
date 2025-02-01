package domain.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;


class MainFoodMenuTest {
    private MainFoodMenu mainFoodMenu;

    @BeforeEach
    void setUp() throws IOException, ParseException {
        mainFoodMenu = new MainFoodMenu();
    }

    @Test
    void testValidateInputNumber_ValidNumber() {
        assertDoesNotThrow(() -> mainFoodMenu.validateInputNumber(2));
    }

    @Test
    void testGetTotalPrice_ValidNumber() {
        int inputNumber = 2;
        int inputQuantity = 2;

        int expectedTotalPrice = mainFoodMenu.getTotalPrice(inputNumber, inputQuantity);

        assertEquals(expectedTotalPrice, 24000);
    }

}

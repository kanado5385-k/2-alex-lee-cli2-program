package domain.menu;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrinkMenuTest {
    private DrinkMenu drinkMenu;

    @BeforeEach
    void setUp() throws IOException, ParseException {
        drinkMenu = new DrinkMenu();
    }

    @Test
    void testValidateInputNumber_ValidNumber() {
        assertDoesNotThrow(() -> drinkMenu.validateInputNumber(1));
    }

    @Test
    void testGetTotalPrice_ValidNumber() {
        int inputNumber = 2;
        int inputQuantity = 2;

        int expectedTotalPrice = drinkMenu.getTotalPrice(inputNumber, inputQuantity);

        assertEquals(expectedTotalPrice, 10000);
    }

}

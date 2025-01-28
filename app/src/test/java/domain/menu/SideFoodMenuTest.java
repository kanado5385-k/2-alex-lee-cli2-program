package domain.menu;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SideFoodMenuTest {
    private SideFoodMenu sideFoodMenu;

    @BeforeEach
    void setUp() throws IOException, ParseException {
        sideFoodMenu = new SideFoodMenu();
    }

    @Test
    void testValidateInputNumber_ValidNumber() {
        assertDoesNotThrow(() -> sideFoodMenu.validateInputNumber(2));
    }

    @Test
    void testGetTotalPrice_ValidNumber() {
        int inputNumber = 2;
        int inputQuantity = 2;

        int expectedTotalPrice = sideFoodMenu.getTotalPrice(inputNumber, inputQuantity);

        assertEquals(expectedTotalPrice, 12000);
    }

}

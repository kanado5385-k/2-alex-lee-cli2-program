package domain.menu.interfaces;

import domain.food.Food;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface MenuInterface {

    public void validateInputNumber(int inputNum);

    public int getTotalPrice(int inputNumber, int inputQuantity);

    public boolean isSufficientQuantity (int inputNumber, int inputQuantity);

    public void decreaseQuantity (int inputNumber, int inputQuantity);


}

package domain.menu.interfaces;

import domain.food.Food;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface MenuInterface {

    public List<Food> readMenuFile() throws IOException, ParseException;

    public void validateInputNumber(int inputNum);

    public int getTotalPrice(int inputNumber, int inputQuantity);
}

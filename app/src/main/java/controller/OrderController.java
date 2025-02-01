package controller;

import domain.menu.SideFoodMenu;
import domain.menu.DrinkMenu;
import domain.menu.MainFoodMenu;
import domain.order.OrderPrice;
import dto.MainMenuDTO;
import org.json.simple.parser.ParseException;
import utilities.InputNumberValidator;
import utilities.Parser;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.List;

public class OrderController {
    private final DrinkMenu drinkMenu;
    private final MainFoodMenu mainFoodMenu;
    private final SideFoodMenu sideFoodMenu;
    private final OrderPrice orderPrice;
    private final InputView inputView;
    private final OutputView outputView;

    public OrderController() throws IOException, ParseException {
        this.drinkMenu = new DrinkMenu();
        this.mainFoodMenu = new MainFoodMenu();
        this.sideFoodMenu = new SideFoodMenu();
        this.orderPrice = new OrderPrice();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void startOrder() {
        outputView.printWelcomeMessage();
        firstOrder();
    }

    private void firstOrder() {
        int selectedMainFoodNumber = readMainMenu();
        int inputQuantityOfMainFood = readQuantityOfMainFood();

        int priceOfMainFood = mainFoodMenu.getTotalPrice(selectedMainFoodNumber,inputQuantityOfMainFood);


    }

    private int readMainMenu() {
        List<MainMenuDTO> mainMenu = MainMenuDTO.getList(mainFoodMenu.getMainFoods());
        outputView.printMainMenu(mainMenu);
        while (true) {
            try {
                String selectedNumber = inputView.readStringNumber();
                int selectedNumberInt = Parser.parseNumberToInt(selectedNumber);
                mainFoodMenu.validateInputNumber(selectedNumberInt);
                return selectedNumberInt;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readQuantityOfMainFood() {
        outputView.printQuantityMessageOfMainFood();
        while (true) {
            try {
                String inputNumber = inputView.readStringNumber();
                int inputNumberInt = Parser.parseNumberToInt(inputNumber);
                InputNumberValidator.validateQuantityOfMainFood(inputNumberInt);
                return inputNumberInt;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

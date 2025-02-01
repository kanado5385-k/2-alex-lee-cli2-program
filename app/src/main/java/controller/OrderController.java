package controller;

import domain.menu.SideFoodMenu;
import domain.menu.DrinkMenu;
import domain.menu.MainFoodMenu;
import domain.order.OrderPrice;
import dto.DrinkDTO;
import dto.MainMenuDTO;
import dto.SideMenuDTO;
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
        int priceOfFirstOrder = firstOrder();

        int priceOfAdditionalOrder = 0;
        while (true) {
            int additionalPurchase = readAdditionalPurchase();
            if (additionalPurchase == 1) {
                int priceOfMainFood = readMainMenu();
                priceOfAdditionalOrder += priceOfMainFood;
            }
            if (additionalPurchase == 2) {
                int priceOfSideFood = readSideMenu();
                priceOfAdditionalOrder += priceOfSideFood;
            }
            if (additionalPurchase == 3) {
                break;
            }
        }
        int totalPrice = priceOfFirstOrder + priceOfAdditionalOrder;
        System.out.println("Total price: " + totalPrice);



    }

    private int firstOrder() {
        int priceOfMainFood = readMainMenu();
        int priceOfSideFood = readSideMenu();
        return priceOfMainFood + priceOfSideFood;
    }

    private int readMainMenu() {
        List<MainMenuDTO> mainMenu = MainMenuDTO.getList(mainFoodMenu.getMainFoods());
        outputView.printMainMenu(mainMenu);
        while (true) {
            try {
                String selectedNumber = inputView.readStringNumber();
                int selectedNumberInt = Parser.parseNumberToInt(selectedNumber);
                mainFoodMenu.validateInputNumber(selectedNumberInt);
                int inputQuantityOfSideFood = readQuantityOfMainFood();
                return mainFoodMenu.getTotalPrice(selectedNumberInt,inputQuantityOfSideFood);
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

    private int readSideMenu() {
        List<SideMenuDTO> sideMenu = SideMenuDTO.getList(sideFoodMenu.getSideFoods());
        List<DrinkDTO> drinksMenu = DrinkDTO.getList(drinkMenu.getDrinks());
        outputView.printSideMenu(sideMenu, drinksMenu);
        while (true) {
            try {
                String inputFormula = inputView.readStringNumber();
                int typeOfSideMenu = InputNumberValidator.validateStartOfFormula(inputFormula);
                int selectedNumberInt = Parser.parseNumberInFormulaToInt(inputFormula);
                if (typeOfSideMenu == 1) {
                    sideFoodMenu.validateInputNumber(selectedNumberInt);
                    int inputQuantityOfSideFood = readQuantityOfSideFood();
                    return sideFoodMenu.getTotalPrice(selectedNumberInt,inputQuantityOfSideFood);
                }
                drinkMenu.validateInputNumber(selectedNumberInt);
                int inputQuantityOfSideFood = readQuantityOfSideFood();
                return drinkMenu.getTotalPrice(selectedNumberInt,inputQuantityOfSideFood);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readQuantityOfSideFood() {
        outputView.printQuantityMessageOfSideFood();
        while (true) {
            try {
                String inputNumber = inputView.readStringNumber();
                int inputNumberInt = Parser.parseNumberToInt(inputNumber);
                InputNumberValidator.validateQuantityOfSideFood(inputNumberInt);
                return inputNumberInt;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readAdditionalPurchase() {
        outputView.printAdditionalPurchaseMessage();
        while (true) {
            try {
                String inputNumber = inputView.readStringNumber();
                int inputNumberInt = Parser.parseNumberToInt(inputNumber);
                InputNumberValidator.validateAdditionalPurchaseNumber(inputNumberInt);
                return inputNumberInt;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

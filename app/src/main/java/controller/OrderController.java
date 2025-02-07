package controller;

import domain.menu.SideFoodMenu;
import domain.menu.DrinkMenu;
import domain.menu.MainFoodMenu;
import domain.order.OrderPrice;
import dto.DrinkDTO;
import dto.MainMenuDTO;
import dto.OrderPriceDTO;
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
    private List<MainMenuDTO> mainMenu;
    private List<SideMenuDTO> sideMenu;
    private List<DrinkDTO> drinksMenu;

    public OrderController() throws IOException, ParseException {
        this.drinkMenu = new DrinkMenu();
        this.mainFoodMenu = new MainFoodMenu();
        this.sideFoodMenu = new SideFoodMenu();
        this.orderPrice = new OrderPrice();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void startOrder() throws IOException {
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
        orderPrice.addOrderPrice(priceOfFirstOrder + priceOfAdditionalOrder);

        String membershipDiscount = readMembershipDiscount();
        if (membershipDiscount.equals("Y")) {
            orderPrice.applyMembershipDiscount();
        }

        String deliverySelect = readDeliverySelect();
        if (deliverySelect.equals("Y")) {
            orderPrice.applyDeliveryPay();
        }

        OrderPriceDTO orderPriceDTO = new OrderPriceDTO(orderPrice.getTotalPrice());
        outputView.printTotalPrice(orderPriceDTO);
        updateMenuFile();
    }

    private int firstOrder() {
        int priceOfMainFood = readMainMenu();
        int priceOfSideFood = readSideMenu();
        return priceOfMainFood + priceOfSideFood;
    }

    private int readMainMenu() {
        mainMenu = MainMenuDTO.getList(mainFoodMenu.getMainFoods());
        outputView.printMainMenu(mainMenu);
        while (true) {
            try {
                String selectedNumber = inputView.readStringAnswer();
                int selectedNumberInt = Parser.parseNumberToInt(selectedNumber);
                mainFoodMenu.validateInputNumber(selectedNumberInt);
                int inputQuantityOfSideFood = readQuantityOfMainFood(selectedNumberInt);
                return mainFoodMenu.getTotalPrice(selectedNumberInt,inputQuantityOfSideFood);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readQuantityOfMainFood(int selectedFoodNumber) {
        outputView.printQuantityMessageOfMainFood();
        while (true) {
            try {
                String inputNumber = inputView.readStringAnswer();
                int inputNumberInt = Parser.parseNumberToInt(inputNumber);
                InputNumberValidator.validateQuantityOfMainFood(inputNumberInt);
                boolean isCanBuy = mainFoodMenu.isSufficientQuantity(selectedFoodNumber, inputNumberInt);
                if (!isCanBuy) {
                    throw new NumberFormatException("[ERROR]: 선택하신 수량이 현재 남은 수량보다 많습니다. 다시 입력해주세요.");
                }
                mainFoodMenu.decreaseQuantity(selectedFoodNumber, inputNumberInt);
                return inputNumberInt;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readSideMenu() {
        sideMenu = SideMenuDTO.getList(sideFoodMenu.getSideFoods());
        drinksMenu = DrinkDTO.getList(drinkMenu.getDrinks());
        outputView.printSideMenu(sideMenu, drinksMenu);
        while (true) {
            try {
                String inputFormula = inputView.readStringAnswer();
                int typeOfSideMenu = InputNumberValidator.validateStartOfFormula(inputFormula);
                int selectedNumberInt = Parser.parseNumberInFormulaToInt(inputFormula);
                if (typeOfSideMenu == 1) {
                    sideFoodMenu.validateInputNumber(selectedNumberInt);
                    int inputQuantityOfSideFood = readQuantityOfSideFood(selectedNumberInt);
                    return sideFoodMenu.getTotalPrice(selectedNumberInt,inputQuantityOfSideFood);
                }
                drinkMenu.validateInputNumber(selectedNumberInt);
                int inputQuantityOfSideFood = readQuantityOfDrink(selectedNumberInt);
                return drinkMenu.getTotalPrice(selectedNumberInt,inputQuantityOfSideFood);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readQuantityOfSideFood(int selectedFoodNumber) {
        outputView.printQuantityMessageOfSideFood();
        while (true) {
            try {
                String inputNumber = inputView.readStringAnswer();
                int inputNumberInt = Parser.parseNumberToInt(inputNumber);
                InputNumberValidator.validateQuantityOfSideFood(inputNumberInt);
                boolean isCanBuy = sideFoodMenu.isSufficientQuantity(selectedFoodNumber, inputNumberInt);
                if (!isCanBuy) {
                    throw new NumberFormatException("[ERROR]: 선택하신 수량이 현재 남은 수량보다 많습니다. 다시 입력해주세요.");
                }
                sideFoodMenu.decreaseQuantity(selectedFoodNumber, inputNumberInt);
                return inputNumberInt;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readQuantityOfDrink(int selectedFoodNumber) {
        outputView.printQuantityMessageOfSideFood();
        while (true) {
            try {
                String inputNumber = inputView.readStringAnswer();
                int inputNumberInt = Parser.parseNumberToInt(inputNumber);
                InputNumberValidator.validateQuantityOfSideFood(inputNumberInt);
                boolean isCanBuy = drinkMenu.isSufficientQuantity(selectedFoodNumber, inputNumberInt);
                if (!isCanBuy) {
                    throw new NumberFormatException("[ERROR]: 선택하신 수량이 현재 남은 수량보다 많습니다. 다시 입력해주세요.");
                }
                drinkMenu.decreaseQuantity(selectedFoodNumber, inputNumberInt);
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
                String inputNumber = inputView.readStringAnswer();
                int inputNumberInt = Parser.parseNumberToInt(inputNumber);
                InputNumberValidator.validateAdditionalPurchaseNumber(inputNumberInt);
                return inputNumberInt;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String readMembershipDiscount() {
        outputView.printMemberShipDiscountMessage();
        while (true) {
            try {
                String inputStr = inputView.readStringAnswer();
                InputNumberValidator.validateYesOrNoAnswer(inputStr);
                return inputStr;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String readDeliverySelect() {
        outputView.printDeliveryMessage();
        while (true) {
            try {
                String inputStr = inputView.readStringAnswer();
                InputNumberValidator.validateYesOrNoAnswer(inputStr);
                return inputStr;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private synchronized void  updateMenuFile() throws IOException {
        mainFoodMenu.saveMenuFile();
        sideFoodMenu.saveMenuFile();
        drinkMenu.saveMenuFile();
    }
}

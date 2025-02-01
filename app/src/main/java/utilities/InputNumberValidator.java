package utilities;

public class InputNumberValidator {
    private static final String SIDE_FOOD = "1";
    private static final String SIDE_DRINK = "2";
    private static final String SEPARATOR = "-";
    public static void validateNumberOfFood(int inputNum, int validNum) {
        if (inputNum > validNum || inputNum <= 0) {
            throw new IllegalArgumentException("[ERROR]: 메뉴판에 없는 숫자를 입력했습니다. 다시 시도해주세요.");
        }
    }

    public static void validateQuantityOfMainFood(int inputNum) {
        if (inputNum > 50) {
            throw new IllegalArgumentException("[ERROR]: 한번에 주문할 수 있는 최대 수량이 50입니다. 다시 시도해주세요.");
        }

        if (inputNum <= 0) {
            throw new IllegalArgumentException("[ERROR]: 자연수를 입력해주셔야합니다. 다시 시도해주세요.");
        }

        if (inputNum < 3) {
            throw new IllegalArgumentException("[ERROR]: 한번에 최소 3인분을 주문해주셔야합니다. 다시 시도해주세요.");
        }
    }

    public static void validateQuantityOfSideFood(int inputNum) {
        if (inputNum > 50) {
            throw new IllegalArgumentException("[ERROR]: 한번에 주문할 수 있는 최대 수량이 50입니다. 다시 시도해주세요.");
        }

        if (inputNum <= 0) {
            throw new IllegalArgumentException("[ERROR]: 자연수를 입력해주셔야합니다. 다시 시도해주세요.");
        }
    }

    public static int validateStartOfFormula(String formula) {
        if (!formula.contains(SEPARATOR)) {
            throw new IllegalArgumentException("[ERROR]: 입력된 형식이 올바르지 않습니다. 다시 시도해주세요.");
        }
        if (formula.startsWith(SIDE_FOOD)) {
            return 1;
        }
        if (formula.startsWith(SIDE_DRINK)) {
            return 2;
        }
        throw new IllegalArgumentException("[ERROR]: 입력된 형식이 올바르지 않습니다. 다시 시도해주세요.");
    }
}

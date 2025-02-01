package utilities;

public class InputNumberValidator {
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
}

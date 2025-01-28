package utilities;

public class InputNumberValidator {
    public static void validateInputNumber(int inputNum, int validNum) {
        if (inputNum > validNum || inputNum <= 0) {
            throw new IllegalArgumentException("메뉴판에 없는 숫자를 입력했습니다. 다시 시도해주세요.");
        }
    }
}

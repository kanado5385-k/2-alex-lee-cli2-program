package utilities;

public class Parser {
    public static Integer parseNumberToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR]: 숫자가 아닌 것이 입력 되었습니다. 다시 시도해주세요.");
        }
    }
}

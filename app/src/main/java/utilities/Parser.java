package utilities;

public class Parser {
    private static final String SEPARATOR = "-";

    public static Integer parseNumberToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR]: 숫자가 아닌 것이 입력 되었습니다. 다시 시도해주세요.");
        }
    }

    public static Integer parseNumberInFormulaToInt(String formula) {
        try {
            int indexOfSeparator = formula.indexOf(SEPARATOR);
            return Integer.parseInt(formula.substring(indexOfSeparator + 1));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR]: 숫자가 아닌 것이 입력 되었습니다. 다시 시도해주세요.");
        }
    }
}

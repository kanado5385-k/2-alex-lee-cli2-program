package view;

import dto.MainMenuDTO;

import java.util.List;
import java.util.Scanner;

public class InputView {
    // Scanner 객체가 여러개 만들어지는 것을 방지하기 위해 static final로 선언
    static final Scanner scanner = new Scanner(System.in);

    public String readStringAnswer() {
        return scanner.nextLine();
    }

}

package view;

import dto.MainMenuDTO;

import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public String readStringNumber() {
        return scanner.nextLine();
    }

}

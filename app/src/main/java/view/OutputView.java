package view;

import dto.MainMenuDTO;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = "========================";

    public void printWelcomeMessage() {
        System.out.println("안녕하세요. 고기의 민족입니다.\n" +
                "  주문을 도와드리겠습니다.\n" +
                DELIMITER);
    }

    public void printMainMenu(List<MainMenuDTO> mainMenu) {
        System.out.println("      🥩메인 메뉴🥩");

        for (MainMenuDTO mainMenuDTO : mainMenu) {
            System.out.printf("No: " + "%d. " + "%s %d" + "g" + " / %d원%n",
                    mainMenuDTO.getNumber(), mainMenuDTO.getName(), mainMenuDTO.getGram(), mainMenuDTO.getPrice());
        }

        System.out.println(System.lineSeparator() + "원하는 메인 메뉴의 번호를 입력해주세요. ");
    }

    public void printQuantityMessageOfMainFood() {
        System.out.println("선택하신 메인 메뉴의 구매 수량을 입력해주세요.\n" +
                "❗️한번에 최소 3인분을 주문해주셔야합니다❗️");
    }
}

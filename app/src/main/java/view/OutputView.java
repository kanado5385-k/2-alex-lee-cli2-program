package view;

import dto.DrinkDTO;
import dto.MainMenuDTO;
import dto.OrderPriceDTO;
import dto.SideMenuDTO;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = "========================";

    public void printWelcomeMessage() {
        System.out.println("🛵안녕하세요, 고기의 민족입니다.🛵\n" +
                "  주문을 도와드리겠습니다.\n" +
                DELIMITER);
    }

    public void printMainMenu(List<MainMenuDTO> mainMenu) {
        System.out.println("      🥩메인 메뉴🥩");

        for (MainMenuDTO mainMenuDTO : mainMenu) {
            System.out.printf("No: " + "%d. " + "%s %d" + "g" + " / %d원" + " - 남은 수량 %d인분%n",
                    mainMenuDTO.getNumber(), mainMenuDTO.getName(), mainMenuDTO.getGram(), mainMenuDTO.getPrice(), mainMenuDTO.getQuantity());
        }

        System.out.println(System.lineSeparator() + "원하는 메인 메뉴의 번호를 입력해주세요. ");
    }

    public void printQuantityMessageOfMainFood() {
        System.out.println(System.lineSeparator() + "선택하신 메인 메뉴의 구매 수량을 입력해주세요.\n" +
                "❗️한번에 최소 3인분을 주문해주셔야합니다❗️");
    }

    public void printSideMenu(List<SideMenuDTO> sideMenu, List<DrinkDTO> drink) {
        System.out.println("     🍢사이드 메뉴🥤");

        for (SideMenuDTO sideMenuDTO : sideMenu) {
            System.out.printf("No: " + "1-%d. " + "%s" + " / %d원" + " - 남은 수량 %d개%n",
                    sideMenuDTO.getNumber(), sideMenuDTO.getName(), sideMenuDTO.getPrice(), sideMenuDTO.getQuantity());
        }

        System.out.println();

        for (DrinkDTO drinkDTO : drink) {
            System.out.printf("No: " + "2-%d. " + "%s %d" + "ml" + " / %d원" + " - 남은 수량 %d개%n",
                    drinkDTO.getNumber(), drinkDTO.getName(), drinkDTO.getMl(), drinkDTO.getPrice(), drinkDTO.getQuantity());
        }

        System.out.println(System.lineSeparator() + "원하는 사이드 메뉴의 번호를 입력해주세요. (반드시 '2-1'와 같은 입력 형식을 지켜주세요.)");
    }

    public void printQuantityMessageOfSideFood() {
        System.out.println(System.lineSeparator() + "선택하신 사이드 메뉴의 구매 수량을 입력해주세요.");
    }

    public void printAdditionalPurchaseMessage() {
        System.out.println(System.lineSeparator() + "추가 구매하실건가요? 결제하실건가요?\n" +
                "No 1: 메인 메뉴 추가 구매 🍖\n" +
                "No 2: 사이드 메뉴 추가 구매 🍤\n" +
                "No 3: 결제하기 💰");
    }

    public void printMemberShipDiscountMessage() {
        System.out.println(System.lineSeparator() + "멤버십 회원이신가요? (Y/N)\n" +
                "⭐️멤버십이라면 10% 할인 받을 수 있습니다.");
    }

    public void printDeliveryMessage() {
        System.out.println(System.lineSeparator() + "배달로 주문하시겠습니까? (Y/N)\n" +
                "📢배달팁이 3000원입니다.");
    }

    public void printTotalPrice(OrderPriceDTO orderPriceDTO) {
        System.out.println(System.lineSeparator() + "💵총 결재금액 :  " + orderPriceDTO.getTotalPrice() + "원 💵");
    }
}

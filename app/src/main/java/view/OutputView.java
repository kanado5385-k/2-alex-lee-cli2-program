package view;

import dto.DrinkDTO;
import dto.MainMenuDTO;
import dto.OrderPriceDTO;
import dto.SideMenuDTO;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = "==========================";

    public void printWelcomeMessage() {
        System.out.println("🛵안녕하세요, 고기의 민족입니다.🛵\n" +
                "    주문을 도와드리겠습니다.\n" +
                DELIMITER);
    }

    public void printMainMenu(List<MainMenuDTO> mainMenu) {
        StringBuilder sb = new StringBuilder();
        //sb.append(System.lineSeparator());
        System.out.println("      🥩메인 메뉴🥩");
        for (MainMenuDTO mainMenuDTO : mainMenu) {
            sb.append(String.format("No: %d. %s %dg / %d원 - 남은 수량 %d인분%n",
                    mainMenuDTO.getNumber(),
                    mainMenuDTO.getName(),
                    mainMenuDTO.getGram(),
                    mainMenuDTO.getPrice(),
                    mainMenuDTO.getQuantity()));
        }
        sb.append("\n원하는 메인 메뉴의 번호를 입력해주세요. \n");
        printWithTypingEffect(sb.toString());
    }

    public void printQuantityMessageOfMainFood() {
        String message = "\n선택하신 메인 메뉴의 구매 수량을 입력해주세요.\n" +
                "❗️한번에 최소 3인분을 주문해주셔야합니다❗️\n";
        printWithTypingEffect(message);
    }

    public void printSideMenu(List<SideMenuDTO> sideMenu, List<DrinkDTO> drink) {
        StringBuilder sb = new StringBuilder();
        sb.append("     🍢사이드 메뉴🥤\n");
        for (SideMenuDTO sideMenuDTO : sideMenu) {
            sb.append(String.format("No: 1-%d. %s / %d원 - 남은 수량 %d개%n",
                    sideMenuDTO.getNumber(),
                    sideMenuDTO.getName(),
                    sideMenuDTO.getPrice(),
                    sideMenuDTO.getQuantity()));
        }
        sb.append("\n");
        for (DrinkDTO drinkDTO : drink) {
            sb.append(String.format("No: 2-%d. %s %dml / %d원 - 남은 수량 %d개%n",
                    drinkDTO.getNumber(),
                    drinkDTO.getName(),
                    drinkDTO.getMl(),
                    drinkDTO.getPrice(),
                    drinkDTO.getQuantity()));
        }
        sb.append("\n원하는 사이드 메뉴의 번호를 입력해주세요. (반드시 '2-1'와 같은 입력 형식을 지켜주세요.)\n");
        printWithTypingEffect(sb.toString());
    }

    public void printQuantityMessageOfSideFood() {
        String message = "\n선택하신 사이드 메뉴의 구매 수량을 입력해주세요.\n";
        printWithTypingEffect(message);
    }

    public void printAdditionalPurchaseMessage() {
        String message = "\n추가 구매하실건가요? 결제하실건가요?\n" +
                "No 1: 메인 메뉴 추가 구매 🍖\n" +
                "No 2: 사이드 메뉴 추가 구매 🍤\n" +
                "No 3: 결제하기 💰\n";
        printWithTypingEffect(message);
    }

    public void printMemberShipDiscountMessage() {
        String message = "\n멤버십 회원이신가요? (Y/N)\n" +
                "⭐️멤버십이라면 10% 할인 받을 수 있습니다.\n";
        printWithTypingEffect(message);
    }

    public void printDeliveryMessage() {
        String message = "\n배달로 주문하시겠습니까? (Y/N)\n" +
                "📢배달팁이 3000원입니다.\n";
        printWithTypingEffect(message);
    }

    public void printTotalPrice(OrderPriceDTO orderPriceDTO) {
        String message = "\n💵총 결재금액 :  " + orderPriceDTO.getTotalPrice() + "원 💵";
        printWithTypingEffect(message);
    }

    private static void printWithTypingEffect(String text) {
        new Thread(() -> {
            try {
                for (char c : text.toCharArray()) {
                    System.out.print(c);
                    Thread.sleep(30); // 30ms 간격으로 출력하기
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 인럽트 상태 유지(30ms 쉬는 동안 다른 작업 요청 거절하기)
            }
        }).start();
    }
}

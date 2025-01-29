package dto;

public class OrderPriceDTO {
    private final int totalPrice;

    public OrderPriceDTO(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}

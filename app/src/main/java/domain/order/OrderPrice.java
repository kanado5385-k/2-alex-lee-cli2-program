package domain.order;

public class OrderPrice {
    private static final double MEMBER_SHIP_DISCOUNT_PERCENT = 0.90;
    private static final int DELIVERY_PAYMENT = 3000;
    private static final int START_PRICE = 0;

    private int totalPrice;

    public OrderPrice() {
        this.totalPrice = START_PRICE;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void addOrderPrice(int amount) {
        totalPrice += amount;
    }

    public void applyMembershipDiscount() {
        totalPrice = (int) (totalPrice * MEMBER_SHIP_DISCOUNT_PERCENT);
    }

    public void applyDeliveryPay() {
        totalPrice = totalPrice + DELIVERY_PAYMENT;
    }
}

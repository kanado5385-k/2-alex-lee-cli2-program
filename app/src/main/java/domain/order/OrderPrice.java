package domain.order;

public class OrderPrice {
    private static final double MEMBER_SHIP_DISCOUNT_PERCENT = 0.95;
    private static final int DELIVERY_PAYMENT = 3000;

    private int totalPrice;

    public OrderPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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

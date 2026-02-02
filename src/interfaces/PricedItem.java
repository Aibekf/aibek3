package interfaces;

public interface PricedItem {
    double getPrice();

    default String priceLabel() {
        return String.format("$%.2f", getPrice());
    }
}

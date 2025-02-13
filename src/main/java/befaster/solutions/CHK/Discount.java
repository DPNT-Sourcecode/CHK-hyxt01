package befaster.solutions.CHK;

import java.util.Optional;

public class Discount {

    private final char sku;
    private final int quantity;
    private final int price;

    public Discount(char sku, int quantity, int price) {
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    public Boolean isApplicable(char sku) {
        return this.sku == sku;
    }

    public Optional<Integer> apply(int count, int unitPrice) {
        if (count < quantity) {
            return Optional.empty();
        }

        int discountCount = count / quantity;
        int remainingCount = count % quantity;

        Integer totalPrice = (discountCount * price) + (remainingCount * unitPrice);
        return Optional.of(totalPrice);
    }
}



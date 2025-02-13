package befaster.solutions.CHK;

import java.util.Optional;

public class Discount {

    private final Character sku;
    private final int requiredQuantity;
    private final int price;

    private int remainingCount;

    public Discount(char sku, int quantity, int price) {
        this.sku = sku;
        this.requiredQuantity = quantity;
        this.price = price;
    }

    public Boolean isApplicable(char sku) {
        return this.sku == sku;
    }

    public Optional<Integer> apply(int count) {
        if (count < requiredQuantity) {
            return Optional.empty();
        }

        int discountCount = count / requiredQuantity;
        remainingCount = count % requiredQuantity;

        Integer totalPrice = (discountCount * price);
        return Optional.of(totalPrice);
    }

    public int getRemainingCount() {
        return remainingCount;
    }
}



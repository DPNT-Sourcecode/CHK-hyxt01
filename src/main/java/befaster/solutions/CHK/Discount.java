package befaster.solutions.CHK;

import java.util.List;
import java.util.Optional;

public class Discount {

    private final List<Character> sku;
    private final int requiredQuantity;
    private final int price;

    private int remainingCount;

    public Discount(char sku, int quantity, int price) {
        this.sku = List.of(sku);
        this.requiredQuantity = quantity;
        this.price = price;
    }

    public Discount(List<Character> charList, int quantity, int price) {
        this.sku = charList;
        this.requiredQuantity = quantity;
        this.price = price;
    }

    public Boolean isApplicable(char sku) {
        return this.sku.contains(sku);
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

    public int getRequiredQuantity() {
        return requiredQuantity;
    }
}



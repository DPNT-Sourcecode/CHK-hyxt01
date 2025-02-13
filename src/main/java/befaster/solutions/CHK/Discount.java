package befaster.solutions.CHK;

import java.util.List;
import java.util.Optional;

public class Discount {

    private final List<Character> sku;
    private final int quantity;
    private final int price;

    private int remainingCount;

    public Discount(char sku, int quantity, int price) {
        this.sku = List.of(sku);
        this.quantity = quantity;
        this.price = price;
    }

    public Discount(List<Character> charList, int quantity, int price) {
        this.sku = charList;
        this.quantity = quantity;
        this.price = price;
    }

    public Boolean isApplicable(char sku) {
        return this.sku.contains(sku);
    }

    public Boolean isGroup

    public Optional<Integer> apply(int count) {
        if (count < quantity) {
            return Optional.empty();
        }

        int discountCount = count / quantity;
        remainingCount = count % quantity;

        Integer totalPrice = (discountCount * price);
        return Optional.of(totalPrice);
    }

    public int getRemainingCount() {
        return remainingCount;
    }
}


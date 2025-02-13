package befaster.solutions.CHK;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CheckoutSolution {

    private static final int INVALID_SKU = -1;
    private static final int EMPTY_CART = 0;
    private static final String VALID_SKU_REGEX = "[A-D]*";

    private final Map<String, Integer> skuPriceMap;
    private final Map<String, Discount> skuDiscountMap;

    public CheckoutSolution() {
        skuPriceMap = Map.of(
            "A", 50,
            "B", 30,
            "C", 20,
            "D", 15
        );

        skuDiscountMap = Map.of(
            "A", new Discount(3, 130),
            "B", new Discount(2, 45)
        );
    }

    public Integer checkout(String skus) {
        boolean isInvalid = skus == null || !skus.matches(VALID_SKU_REGEX);
        if (isInvalid) {
            return INVALID_SKU;
        }

        boolean isEmpty = skus.isEmpty();
        if (isEmpty) {
            return EMPTY_CART;
        }

        Map<Character, Integer> skuCountMap = new HashMap<>();
        for (char sku : skus.toCharArray()) {
            skuCountMap.put(sku, skuCountMap.getOrDefault(sku, 0) + 1);
        }

        int total = 0;
        for (Map.Entry<Character, Integer> entry : skuCountMap.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();

            total += calculatePrice(sku, count);
        }
        return total;
    }

    private int calculatePrice(char sku, int count) {
        String skuStr = String.valueOf(sku);
        int price = skuPriceMap.get(skuStr);

        Discount discount = skuDiscountMap.get(skuStr);
        if (discount != null) {
            Optional<Integer> discountedPrice = discount.apply(count, price);
            if (discountedPrice.isPresent()) {
                return discountedPrice.get();
            }
        }
        return count * price;
    }

    private class Discount {
        private final int quantity;
        private final int price;

        public Discount(int quantity, int price) {
            this.quantity = quantity;
            this.price = price;
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
}

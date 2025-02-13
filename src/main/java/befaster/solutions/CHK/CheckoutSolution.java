package befaster.solutions.CHK;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CheckoutSolution {

    private static final int INVALID_SKU = -1;
    private static final int EMPTY_CART = 0;
    private static final String VALID_SKU_REGEX = "[A-E]*";

    private final Map<String, Integer> skuPriceMap;
    private final List<Discount> discountList;
    private final BuyXGetYFreeOffer freeBWithE;

    public CheckoutSolution() {
        skuPriceMap = Map.of(
            "A", 50,
            "B", 30,
            "C", 20,
            "D", 15,
            "E", 40
        );

        discountList = List.of(
                new Discount('A', 5, 200),
                new Discount('A', 3, 130),
                new Discount('B', 2, 45)
        );
        freeBWithE = new BuyXGetYFreeOffer('E', 2, 'B');
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

        freeBWithE.apply(skuCountMap);

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
        int unitPrice = skuPriceMap.get(skuStr);

        int value = 0;
        for (Discount discount : discountList) {
            if (discount.isApplicable(sku)) {
                Optional<Integer> valueWithDiscount = discount.apply(count, unitPrice);
                if (valueWithDiscount.isPresent()) {
                    value += valueWithDiscount.get();
                    count = discount.getRemainingCount();
                }
            }
        }

        value += count * unitPrice;
        return value;
    }
}



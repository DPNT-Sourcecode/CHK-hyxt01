package befaster.solutions.CHK;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CheckoutSolution {

    private static final int INVALID_SKU = -1;
    private static final int EMPTY_CART = 0;
    private static final String VALID_SKU_REGEX = "[A-F]*";

    private final Map<String, Integer> skuPriceMap;
    private final DiscountManager discountManager;

    public CheckoutSolution() {
        skuPriceMap = Map.of(
            "A", 50,
            "B", 30,
            "C", 20,
            "D", 15,
            "E", 40,
            "F", 10,
            "G", 20,
            "H", 10,
            "I", 35,
            "J", 60,
            "K", 70,
            "L", 90,
            "M", 15,
            "N", 40,
            "O", 10,
            "P", 50,
            "Q", 30,
            "R", 50,
            "S", 20,
            "T", 20,
            "U", 40,
            "V", 50,
            "W", 20,
            "X", 90,
            "Y", 10,
            "Z", 50
        );
        discountManager = new DiscountManager();
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

        discountManager.applyFreeItemOffers(skuCountMap);

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

        return discountManager.calculateValueWithDiscount(sku, count, unitPrice);
    }
}

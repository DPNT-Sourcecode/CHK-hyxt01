package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private static final int INVALID_SKU = -1;
    private static final int EMPTY_CART = 0;
    private static final String VALID_SKU_REGEX = "[A-Z]*";

    private final Map<String, Integer> skuPriceMap;
    private final DiscountManager discountManager;

    public CheckoutSolution() {
        skuPriceMap = new ProductCollection().getProducts();
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

        discountManager.dropFreeItemOffers(skuCountMap);

        int total = 0;
        total += discountManager.applyGroupDiscounts(skuCountMap);

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
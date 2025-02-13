package befaster.solutions.CHK;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GroupDiscountManager {

    List<Discount> groupDiscountList = List.of(
            new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45)
    );

    public int count = 0;
    public Map<Character, Integer> remaining;
    public int checkoutValue;

    public GroupDiscountManager(Map<Character, Integer> skuCountMap) {
        applyGroupDiscounts(skuCountMap);
    }

    public List<Character> getNonApplicableSkus() {
        return remaining;
    }

    private void applyGroupDiscounts(Map<Character, Integer> skuCountMap) {
        int value = 0;
        for (Discount groupDiscount : groupDiscountList) {
            int skuGroupCount = 0;
            for (char sku : groupDiscount.getSkuList()) {
                skuGroupCount += skuCountMap.getOrDefault(sku, 0);
            }

            Optional<Integer> valueWithDiscount = groupDiscount.apply(skuGroupCount);
            if (valueWithDiscount.isPresent()) {
                value += valueWithDiscount.get();
                for (char sku : groupDiscount.getSkuList()) {
                    remaining.put(sku, skuCountMap.getOrDefault(sku, 0) - skuGroupCount);
                }
            }
        }
        return value;
    }
}


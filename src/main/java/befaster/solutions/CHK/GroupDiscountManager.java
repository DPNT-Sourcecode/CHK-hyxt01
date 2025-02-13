package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GroupDiscountManager {

    List<Discount> groupDiscountList = List.of(
            new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45)
    );

    public Map<Character, Integer> nonApplicableSkus;
    public int groupedDiscountValue = 0;

    public GroupDiscountManager(Map<Character, Integer> skuCountMap) {
        this.nonApplicableSkus = skuCountMap;
        applyGroupDiscounts(skuCountMap);
    }

    Map<Character, Integer> getNonApplicableSkus() {
        return nonApplicableSkus;
    }

    private void applyGroupDiscounts(Map<Character, Integer> skuCountMap) {
        for (Discount groupDiscount : groupDiscountList) {
            List<Character> skuInGroup = new ArrayList<>();
            int skuGroupCount = 0;
            for (char sku : groupDiscount.getSkuList()) {
                skuGroupCount += skuCountMap.getOrDefault(sku, 0);
                skuInGroup.add(sku);
            }

            Optional<Integer> valueWithDiscount = groupDiscount.apply(skuGroupCount);
            if (valueWithDiscount.isPresent()) {
                groupedDiscountValue += valueWithDiscount.get();

                for (char sku : skuInGroup) {
                    int remainingCount = skuCountMap.getOrDefault(sku, 0) - 1;
                    if (remainingCount >= 0) this.nonApplicableSkus.put(sku, remainingCount);
                }
            }
        }
    }
}


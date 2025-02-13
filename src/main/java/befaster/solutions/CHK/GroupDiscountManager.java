package befaster.solutions.CHK;

import java.util.*;

public class GroupDiscountManager {

    private static final Discount groupDiscountItems = new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45);


    public Map<Character, Integer> nonApplicableSkus;
    public int groupedDiscountValue = 0;

    public GroupDiscountManager(Map<Character, Integer> skuCountMap) {
        this.nonApplicableSkus = skuCountMap;
        apply(skuCountMap);
    }

    Map<Character, Integer> getNonApplicableSkus() {
        return nonApplicableSkus;
    }
//
//    private void applyGroupDiscounts(Map<Character, Integer> skuCountMap) {
//        for (Discount groupDiscount : groupDiscountList) {
//            List<Character> skuInGroup = new ArrayList<>();
//            int skuGroupCount = 0;
//            for (char sku : groupDiscount.getSkuList()) {
//                int skuItemGroup = skuCountMap.getOrDefault(sku, 0);
//                if (skuItemGroup == 0) continue;
//
//                skuGroupCount += skuItemGroup;
//                for (int i = 0; i < skuItemGroup; i++) {
//                    skuInGroup.add(sku);
//                }
//            }
//
//            Optional<Integer> valueWithDiscount = groupDiscount.apply(skuGroupCount);
//            if (valueWithDiscount.isPresent()) {
//                groupedDiscountValue += valueWithDiscount.get();
//
//                for (char sku : skuInGroup) {
//                    int remainingCount = skuCountMap.getOrDefault(sku, 0) - 1;
//                    if (remainingCount >= 0) this.nonApplicableSkus.put(sku, remainingCount);
//                }
//            }
//        }
//    }

    private void apply(Map<Character, Integer> skuCountMap) {
        List<Character> itemsInGroup = getGroupItems(skuCountMap);
        int total = 0;
        while (itemsInGroup.size() >= groupDiscountItems.getRequiredQuantity()) {
            total += 45;
            itemsInGroup.subList(0, 3).clear();
        }
        for (char item : itemsInGroup) {
            total += prices.get(item);
        }
    }

    private List<Character> getGroupItems(Map<Character, Integer> skuCountMap) {
        List<Character> skuInGroup = new ArrayList<>();
        for (char item : groupDiscountItems.getSkuList()) {
            for (int i = 0; i < skuCountMap.getOrDefault(item, 0); i++) {
                skuInGroup.add(item);
            }
        }
        return skuInGroup;
    }
}






package befaster.solutions.CHK;

import java.util.*;

public class GroupDiscountManager {

    private static final Set<Character> GROUP_DISCOUNT_ITEMS = Set.of('S', 'T', 'X', 'Y', 'Z');
    private static final int REQUIRED_QUANTITY = 3;
    private static final int PRICE = 45;


    public Map<Character, Integer> nonApplicableSkus;
    public int groupedDiscountValue = 0;

    public GroupDiscountManager(Map<Character, Integer> skuCountMap) {
        this.nonApplicableSkus = skuCountMap;
        apply(skuCountMap);
    }

    Map<Character, Integer> getNonApplicableSkus() {
        return nonApplicableSkus;
    }

//    private void applyGroupDiscounts(Map<Character, Integer> skuCountMap) {
//        List<Character> skuInGroup = new ArrayList<>();
//        int skuGroupCount = 0;
//        for (char sku : groupDiscountItems.getSkuList()) {
//            int skuItemGroup = skuCountMap.getOrDefault(sku, 0);
//            if (skuItemGroup == 0) continue;
//
//            skuGroupCount += skuItemGroup;
//            for (int i = 0; i < skuItemGroup; i++) {
//                skuInGroup.add(sku);
//            }
//        }
//
//        Optional<Integer> valueWithDiscount = groupDiscount.apply(skuGroupCount);
//        if (valueWithDiscount.isPresent()) {
//            groupedDiscountValue += valueWithDiscount.get();
//
//            for (char sku : skuInGroup) {
//                int remainingCount = skuCountMap.getOrDefault(sku, 0) - 1;
//                if (remainingCount >= 0) this.nonApplicableSkus.put(sku, remainingCount);
//            }
//        }
//    }

    private void apply(Map<Character, Integer> skuCountMap) {
        List<Character> itemsInGroup = new ArrayList<>();
        for (char item : GROUP_DISCOUNT_ITEMS) {
            for (int i = 0; i < skuCountMap.getOrDefault(item, 0); i++) {
                itemsInGroup.add(item);
            }
        }

        while (itemsInGroup.size() >= REQUIRED_QUANTITY) {
            groupedDiscountValue += PRICE;
            itemsInGroup.subList(0, REQUIRED_QUANTITY).forEach(item -> {
                int remainingCount = skuCountMap.getOrDefault(item, 0) - 1;
                if (remainingCount > 0) this.nonApplicableSkus.put(item, remainingCount);
                if (remainingCount == 0) this.nonApplicableSkus.remove(item);
            });
            itemsInGroup.subList(0, REQUIRED_QUANTITY).clear();
        }
    }

    private List<Character> getGroupItems(Map<Character, Integer> skuCountMap) {
        List<Character> skuInGroup = new ArrayList<>();
        for (char item : GROUP_DISCOUNT_ITEMS) {
            for (int i = 0; i < skuCountMap.getOrDefault(item, 0); i++) {
                skuInGroup.add(item);
            }
        }
        return skuInGroup;
    }
}





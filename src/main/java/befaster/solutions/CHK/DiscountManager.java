package befaster.solutions.CHK;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DiscountManager {

    private final List<Discount> discountList;
    private final List<BuyXGetYFreeOffer> freeItemOffers;
    private final List<Discount> groupDiscountList;

    public DiscountManager() {
        discountList = List.of(
                new Discount('A', 5, 200),
                new Discount('A', 3, 130),
                new Discount('B', 2, 45),
                new Discount('H', 10, 80),
                new Discount('H', 5, 45),
                new Discount('K', 2, 150),
                new Discount('P', 5, 200),
                new Discount('Q', 3, 80),
                new Discount('V', 3, 130),
                new Discount('V', 2, 90)
        );
        groupDiscountList = List.of(
                new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45)
        );
        freeItemOffers = List.of(
                new BuyXGetYFreeOffer('E', 2, 'B'),
                new BuyXGetYFreeOffer('F', 3, 'F'),
                new BuyXGetYFreeOffer('N', 3, 'M'),
                new BuyXGetYFreeOffer('R', 3, 'Q'),
                new BuyXGetYFreeOffer('U', 4, 'U')
        );
    }

    public void dropFreeItemOffers(Map<Character, Integer> skuCountMap) {
        freeItemOffers.forEach(freeItemOffer -> freeItemOffer.apply(skuCountMap));
    }

    public int applyGroupDiscounts(Map<Character, Integer> skuCountMap) {
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
                    skuCountMap.put(sku, skuCountMap.getOrDefault(sku, 0) - skuGroupCount);
                }
            }
        }
        return value;
    }

    public int calculateValueWithDiscount(char sku, int count, int unitPrice) {
        int value = 0;

        for (Discount discount : discountList) {
            if (discount.isApplicable(sku)) {
                Optional<Integer> valueWithDiscount = discount.apply(count);
                if (valueWithDiscount.isPresent()) {
                    value += valueWithDiscount.get();
                    count = discount.getRemainingCount();
                    continue;
                }
            }
        }

        value += count * unitPrice;
        return value;
    }
}



package befaster.solutions.CHK;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DiscountManager {

    private final List<Discount> discountList;
    private final List<BuyXGetYFreeOffer> freeItemOffers;

    public DiscountManager() {
        discountList = List.of(
                new Discount('A', 5, 200),
                new Discount('A', 3, 130),
                new Discount('B', 2, 45)
        );
        freeItemOffers = List.of(
                new BuyXGetYFreeOffer('E', 2, 'B'),
                new BuyXGetYFreeOffer('F', 3, 'F')
        );
    }

    public void applyFreeItemOffers(Map<Character, Integer> skuCountMap) {
        freeItemOffers.forEach(freeItemOffer -> freeItemOffer.apply(skuCountMap));
    }

    public int calculateValueWithDiscount(char sku, int count, int unitPrice) {
        int value = 0;

        for (Discount discount : discountList) {
            if (discount.isApplicable(sku)) {
                Optional<Integer> valueWithDiscount = discount.apply(count);
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

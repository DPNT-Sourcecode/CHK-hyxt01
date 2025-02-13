package befaster.solutions.CHK;

import java.util.List;
import java.util.Optional;

public class GroupDiscountManager {

    List<Discount> discountList = List.of(
            new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45)
    );

    public int value;
    public List<Character> remainingItems;

    public int apply(List<Character> skuList) {
        List<Character> remainingItems = skuList;

        for (Discount discount : discountList) {
            int skuGroupCount = 0;
            for (char sku : list) {
                if (discount.isApplicable(sku)) {
                    skuGroupCount++;
                    remainingItems.remove(sku);
                }
            }
            Optional<Integer> valueWithDiscount = discount.apply(skuGroupCount);
            if (valueWithDiscount.isPresent()) {
                value += valueWithDiscount.get();
            }
        }
        return -1;
    }
}


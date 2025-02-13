package befaster.solutions.CHK;

import java.util.List;
import java.util.Optional;

public class GroupDiscountManager {

    List<Discount> discountList = List.of(
            new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45)
    );

    public int apply(List<Character> list) {
        for (Discount discount : discountList) {
            int skuGroupCount = 0;
            for (char sku : list) {
                if (discount.isApplicable(sku)) {
                    skuGroupCount++;
                }
            }
            Optional<Integer> valueWithDiscount = discount.apply(skuGroupCount);
            if (valueWithDiscount.isPresent()) {
                return valueWithDiscount.get();
            }
        }
        return -1;
    }
}

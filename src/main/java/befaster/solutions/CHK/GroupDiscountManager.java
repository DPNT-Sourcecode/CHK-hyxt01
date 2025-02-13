package befaster.solutions.CHK;

import java.util.List;

public class GroupDiscountManager {

    List<Discount> discountList = List.of(
            new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45)
    );

    public int apply(List<Character> list) {
        int value = 0;
        for (Discount discount : discountList) {
            int skuGroupCount = 0;
            for (char sku : list) {
                if (discount.isApplicable(sku)) {
                    skuGroupCount++;
                }
            }
            value += discount.apply(skuGroupCount);
        }
        return value;
    }
}


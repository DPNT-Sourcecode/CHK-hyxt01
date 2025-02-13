package befaster.solutions.CHK;

import java.util.List;
import java.util.Optional;

public class GroupDiscountManager {

    List<Discount> discountList = List.of(
            new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45)
    );

    public int value;
    public List<Character> remainingSkuList;

    public int apply(String skuList) {
//        remainingSkuList = List.of(skuList.toCharArray());
//
//        for (Discount discount : discountList) {
//            int skuGroupCount = 0;
//            for (char sku : skuList.toCharArray()) {
//                if (discount.isApplicable(sku)) {
//                    skuGroupCount++;
//                    remainingSkuList.remove(sku);
//                }
//            }
//            Optional<Integer> valueWithDiscount = discount.apply(skuGroupCount);
//            if (valueWithDiscount.isPresent()) {
//                value += valueWithDiscount.get();
//            } else {
//                remainingSkuList = skuList;
//            }
//        }
        return -1;
    }
}



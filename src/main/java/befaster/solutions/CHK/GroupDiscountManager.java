package befaster.solutions.CHK;

import java.util.List;

public class GroupDiscountManager {

    List<Discount> discountList = List.of(
            new Discount(List.of('S', 'T', 'X', 'Y', 'Z'), 3, 45)
    );

    public boolean isApplicable(List<Character> list) {
        boolean isApplicable = discountList.stream().anyMatch(discount -> discount.isApplicable(list));
        discountList.forEach(discount -> {
            if (discount.isApplicable(list)) {
                discount.apply(list);
            }
        });
    }
}

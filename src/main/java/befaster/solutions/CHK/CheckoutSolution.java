package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.Map;

public class CheckoutSolution {

    private static final Integer INVALID_SKU = -1;
    private static final String VALID_SKU_REGEX = "[A-D]*";

    private final Map<String, Integer> skuPriceMap;
    private final Map<String, Integer> skuDiscountMap;

    public CheckoutSolution() {
        skuPriceMap = Map.of(
            "A", 50,
            "B", 30,
            "C", 20,
            "D", 15
        );

        skuDiscountMap = Map.of(
            "A", 130,
            "B", 45
        );
    }

    public Integer checkout(String skus) {
        boolean isInvalid = skus == null || skus.isEmpty() || !skus.matches(VALID_SKU_REGEX);
        if (isInvalid) {
            return INVALID_SKU;
        }


    }

    private class Discount {
        private final String sku;
        private final Integer quantity;
        private final Integer price;

        public Discount(String sku, Integer quantity, Integer price) {
            this.sku = sku;
            this.quantity = quantity;
            this.price = price;
        }

        public String getSku() {
            return sku;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Integer getPrice() {
            return price;
        }
    }
}

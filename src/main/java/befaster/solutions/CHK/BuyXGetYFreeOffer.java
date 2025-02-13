package befaster.solutions.CHK;

import java.util.Map;

public class BuyXGetYFreeOffer {

    private final char buySku;
    private final int requiredQuantity;
    private final char freeSku;

    public BuyXGetYFreeOffer(char buySku, int requiredQuantity, char freeSku) {
        this.buySku = buySku;
        this.requiredQuantity = requiredQuantity;
        this.freeSku = freeSku;
    }

    public void apply(Map<Character, Integer> itemCounts) {
        int bought = itemCounts.getOrDefault(buySku, 0);
        if (bought < requiredQuantity) return;

        int freeCount = bought / requiredQuantity;
        itemCounts.put(freeSku, Math.max(0, itemCounts.getOrDefault(freeSku, 0) - freeCount));
    }
}

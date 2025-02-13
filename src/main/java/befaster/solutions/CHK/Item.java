package befaster.solutions.CHK;

public class Item {

    private final char sku;
    private final int unitPrice;
    private 

    public Item(char sku, int price) {
        this.sku = sku;
        this.unitPrice = price;
    }

    public char getSku() {
        return sku;
    }

    public void markAsGroupingOffer() {
        this.unitPrice = -1;
    }
}

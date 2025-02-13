package befaster.solutions.CHK;

public class GroupItem {

    private final char sku;
    private final int unitPrice;

    private int finalPrice;
    private boolean belongsToGroupPromotion = false;

    public GroupItem(char sku, int price) {
        this.sku = sku;
        this.unitPrice = price;
        this.finalPrice = price;
    }

    public char getSku() {
        return sku;
    }

    public void markAsGroupingOffer() {
        this.belongsToGroupPromotion = true;
    }

    public void setAsFree() {
        this.finalPrice = 0;
    }

    public int getPrice() {
        return finalPrice;
    }
}

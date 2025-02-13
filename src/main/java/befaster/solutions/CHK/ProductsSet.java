package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class ProductsSet {

    private Map<String, Integer> products;

    public ProductsSet() {
        this.products = new HashMap<>();
        this.products.put("A", 50);
        this.products.put("B", 30);
        this.products.put("C", 20);
        this.products.put("D", 15);
        this.products.put("E", 40);
        this.products.put("F", 10);
        this.products.put("G", 20);
        this.products.put("H", 10);
        this.products.put("I", 35);
        this.products.put("J", 60);
        this.products.put("K", 80);
        this.products.put("L", 90);
        this.products.put("M", 15);
        this.products.put("N", 40);
        this.products.put("O", 10);
        this.products.put("P", 50);
        this.products.put("Q", 30);
        this.products.put("R", 50);
        this.products.put("S", 30);
        this.products.put("T", 20);
        this.products.put("U", 40);
        this.products.put("V", 50);
        this.products.put("W", 20);
        this.products.put("X", 90);
        this.products.put("Y", 10);
        this.products.put("Z", 50);
    }

    public Map<String, Integer> getProducts() {
        return this.products;
    }
}


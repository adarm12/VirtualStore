package org.example;

import java.util.Arrays;

public class ShoppingCart {
    private Product[] products;

    public ShoppingCart(Product[] products) {
        this.products = products;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + Arrays.toString(products) +
                '}';
    }

}

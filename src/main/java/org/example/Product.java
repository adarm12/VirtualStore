package org.example;

public class Product {
   private String name;
   private double price;
    private double discount;
    private boolean isInStock;

    public Product(String name, double price, double discount, boolean isInStock) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.isInStock = isInStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", isInStock=" + isInStock +
                '}';
    }

}

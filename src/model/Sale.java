package model;

public class Sale {
    private int saleID;
    private Product product;
    private int quantity;
    private double price;
    private Customer customer;
    public Sale() {
    }

    public Sale(int saleID, Product product, int quantity, double price, Customer customer) {
        this.saleID = saleID;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.customer = customer;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

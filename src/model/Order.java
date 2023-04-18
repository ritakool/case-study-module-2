package model;

import java.util.List;


public class Order {
    private int oderId;
    private Customer customer;
    private List<Product> productLists;
    private Address shippingAddress;
    private statusOrder orderStatus;

    public Order(int oderId, Customer customer, List<Product> productLists, Address shippingAddress, statusOrder orderStatus) {
        this.oderId = oderId;
        this.customer = customer;
        this.productLists = productLists;
        this.shippingAddress = shippingAddress;
        this.orderStatus = orderStatus;
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProductLists() {
        return productLists;
    }

    public void setProductLists(List<Product> productLists) {
        this.productLists = productLists;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public statusOrder getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(statusOrder orderStatus) {
        this.orderStatus = orderStatus;
    }
    public enum statusOrder {
        COMPLETE, SENDING, CANCELLED
    }
}

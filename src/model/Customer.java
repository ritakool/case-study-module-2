package model;

import model.Address;

public class Customer {
    private int id;
    private int phoneNumber;
    private String name;
    private Address address;
    public Customer() {
    }

    public Customer(int id, int phoneNumber, String name, Address address) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}


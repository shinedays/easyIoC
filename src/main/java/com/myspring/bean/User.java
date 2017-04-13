package com.myspring.bean;

/**
 * Created by bupt on 4/12/17.
 */
public class User {
    private String username;
    private Address address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                ", address=" + address +
                '}';
    }
}

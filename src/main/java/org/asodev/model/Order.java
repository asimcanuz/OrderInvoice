package org.asodev.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {

    // This is the order number
    private String orderNumber;
    // This is the total amount of the order
    private double amount;

    // This is a map of products and their quantities
    private HashMap<Product, Integer> productList = new HashMap<>();

    private Customer customer;

    public Order(String orderNumber, double amount, HashMap<Product, Integer> productList, Customer customer) {
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.productList = productList;
        this.customer = customer;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public HashMap<Product, Integer> getProductList() {
        return productList;
    }

    public void setProductList(HashMap<Product, Integer> productList) {
        this.productList = productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", amount=" + amount +
                ", productList=" + productList +
                ", customer=" + customer +
                '}';
    }

}

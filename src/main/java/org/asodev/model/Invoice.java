package org.asodev.model;

import java.util.Objects;

public class Invoice {

    private String invoiceNumber;
    private double amount;
    private boolean paid;
    private Order order;

    private Invoice(){

    }

    public Invoice(String invoiceNumber, double amount, Order order) {
        this.invoiceNumber = invoiceNumber;
        this.order = order;
        this.amount = amount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceNumber, invoice.invoiceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", amount=" + amount +
                ", paid=" + paid +
                ", order=" + order +
                '}';
    }
}

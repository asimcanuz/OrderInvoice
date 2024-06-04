package org.asodev.repository;

import org.asodev.model.Order;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OrderRepository {
    private final Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Set<Order> findAll() {
        return orders;
    }

    public Optional<Order> getByOrderNumber(String number) {
        return orders.stream()
                .filter(order -> order.getOrderNumber().equals(number))
                .findFirst();
    }
}

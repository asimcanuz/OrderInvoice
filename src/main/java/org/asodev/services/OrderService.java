package org.asodev.services;

import org.asodev.model.Customer;
import org.asodev.model.Order;
import org.asodev.model.Product;
import org.asodev.repository.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {

    private static final AtomicInteger orderCounter = new AtomicInteger(0);


    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Order addOrder(HashMap<Product,Integer> productList, Customer customer){
        String orderNo = generateOrderNo();
        double amount = 0;

        for (Product product : productList.keySet()) {
            int quantity = productList.get(product);
            amount += product.getPrice() * quantity;
            productService.decreaseStock(product, quantity);
        }

        Order order = new Order(orderNo, amount,productList,customer);
        orderRepository.addOrder(order);
        return order;
    }

    public Optional<Order> getOrderByOrderNumber(String orderNumber){
        return orderRepository.getByOrderNumber(orderNumber);
    }

    public Set<Order> findAll(){
        return orderRepository.findAll();
    }


    public static String generateOrderNo(){
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "ORD" + timeStamp + orderCounter.getAndIncrement();
    }
}

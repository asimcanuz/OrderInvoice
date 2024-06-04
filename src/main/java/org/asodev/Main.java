package org.asodev;

import org.asodev.model.*;
import org.asodev.repository.*;
import org.asodev.services.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        CompanyRepository companyRepository = new CompanyRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        InvoiceRepository invoiceRepository = new InvoiceRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();

        CompanyService companyService = new CompanyService(companyRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        ProductService productService = new ProductService(productRepository, companyService);
        OrderService orderService = new OrderService(orderRepository, productService);
        InvoiceService invoiceService = new InvoiceService(invoiceRepository, orderService);

        Company company1 = companyService.save("Company 1", "Company 1 Address", "Company 1 Phone", "Company 1 Email");
        Company company2 = companyService.save("Company 2", "Company 2 Address", "Company 2 Phone", "Company 2 Email");
        Company company3 = companyService.save("Company 3", "Company 3 Address", "Company 3 Phone", "Company 3 Email");
        companyService.findAll().forEach(System.out::println);
        System.out.println("-------------------------\n");

        Customer customer1 = customerService.save("Customer 1", "Customer 1 Address", "Customer 1 Phone", "Customer 1 Email");
        Customer customer2 = customerService.save("Asım", "Customer 2 Address", "Customer 2 Phone", "Customer 2 Email");
        Customer customer3 = customerService.save("Customer 3", "Customer 3 Address", "Customer 3 Phone", "Customer 3 Email");
        customerService.findAll().forEach(System.out::println);
        System.out.println("-------------------------\n");

        Product product1 = productService.addProduct("Product 1", 100.0, "Company 1");
        Product product2 = productService.addProduct("Product 2", 200.0, "Company 1");
        Product product3 = productService.addProduct("Product 3", 300.0, "Company 3");
        productService.findAll().forEach(System.out::println);
        System.out.println("-------------------------\n");

        HashMap<Product,Integer> orderProducts = new HashMap<>();
        orderProducts.put(product1, 2);
        orderProducts.put(product2, 3);
        HashMap<Product,Integer> order2Products = new HashMap<>();
        order2Products.put(product2, 20);
        order2Products.put(product3, 1);
        order2Products.put(product1, 10);
        HashMap<Product,Integer> order3Products = new HashMap<>();
        order3Products.put(product2, 20);
        order3Products.put(product3, 1);
        order3Products.put(product1, 10);

        Order order1 = orderService.addOrder(orderProducts,customer1);
        Order order2 = orderService.addOrder(order2Products,customer2);
        Order order3 = orderService.addOrder(order3Products,customer3);
        orderService.findAll().forEach(System.out::println);
        System.out.println("-------------------------\n");

        invoiceService.createInvoice(order1.getOrderNumber());
        invoiceService.createInvoice(order2.getOrderNumber());
        invoiceService.createInvoice(order3.getOrderNumber());
        invoiceService.getInvoices().forEach(System.out::println);
        System.out.println("-------------------------\n");

        System.out.println("İsminde C harfi geçen müşteriler");
        customerService.getCustomersByCharacter("C").forEach(System.out::println);
        System.out.println("-------------------------\n");

        System.out.println("Haziranda kayıt olan müşteriler");
        customerService.getCustomersByMonth(6).forEach(System.out::println);
        System.out.println("-------------------------\n");

        System.out.println("Sistemdeki 1500TL üstündeki faturaları listeleyin");
        invoiceService.filterInvoicesWithPrice(1500.0,">").forEach(System.out::println);
        System.out.println("-------------------------\n");

        System.out.println("Sistemdeki 1500TL üstündeki faturaların ortalaması");
        invoiceService.filterInvoicesWithPrice(1500.0,">").stream().mapToDouble(Invoice::getAmount).average().ifPresent(System.out::println);
        System.out.println("-------------------------\n");


    }
}
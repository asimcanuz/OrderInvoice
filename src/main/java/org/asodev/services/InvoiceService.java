package org.asodev.services;

import org.asodev.model.Invoice;
import org.asodev.model.Order;
import org.asodev.repository.InvoiceRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class InvoiceService {
    private static final AtomicInteger invoiceCounter = new AtomicInteger(0);

    private final InvoiceRepository invoiceRepository;
    private final OrderService orderService;

    public InvoiceService(InvoiceRepository invoiceRepository, OrderService orderService) {
        this.invoiceRepository = invoiceRepository;
        this.orderService = orderService;
    }


    public Invoice createInvoice(String orderNo) {
        Optional<Order> optionOrder = orderService.getOrderByOrderNumber(orderNo);
        if (optionOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order order = optionOrder.get();
        Invoice invoice = new Invoice(generateInvoiceNo(), order.getAmount(), order);
        return invoiceRepository.addInvoice(invoice);
    }

    public Set<Invoice> getInvoices() {
        return invoiceRepository.getInvoices();
    }

    public List<Invoice> filterInvoicesWithPrice(double price, String operator) {
        return invoiceRepository.filterInvoicesWithPrice(price, operator);
    }

    public static String generateInvoiceNo() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "INV" + timeStamp + invoiceCounter.getAndIncrement();
    }
}

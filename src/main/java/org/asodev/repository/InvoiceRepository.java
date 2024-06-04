package org.asodev.repository;

import org.asodev.model.Invoice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InvoiceRepository {
    private final Set<Invoice> invoices = new HashSet<>();

    public Invoice addInvoice(Invoice invoice) {
        invoices.add(invoice);
        return invoice;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public Invoice getInvoice(String number) {
        return invoices.stream()
                .filter(invoice -> invoice.getInvoiceNumber().equals(number))
                .findFirst()
                .orElse(null);
    }

    public List<Invoice> filterInvoicesWithPrice(double price, String operator) {
        return invoices.stream()
                .filter(invoice -> switch (operator) {
                    case "<" -> invoice.getAmount() < price;
                    case ">" -> invoice.getAmount() > price;
                    case "<=" -> invoice.getAmount() <= price;
                    case ">=" -> invoice.getAmount() >= price;
                    case "==" -> invoice.getAmount() == price;
                    default -> false;
                })
                .toList();
    }


}

package org.asodev.repository;

import org.asodev.model.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductRepository {
    private final Set<Product> products = new HashSet<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Set<Product> findAll() {
        return products;
    }

    public Product getProduct(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void decreaseStock(Product product, int quantity) {
        products.stream()
                .filter(p -> p.equals(product))
                .findFirst()
                .ifPresent(productToUpdate -> productToUpdate.setStock(productToUpdate.getStock() - quantity));

    }
}

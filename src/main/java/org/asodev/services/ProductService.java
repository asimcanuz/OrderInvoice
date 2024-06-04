package org.asodev.services;

import org.asodev.model.Company;
import org.asodev.model.Customer;
import org.asodev.model.Product;
import org.asodev.repository.ProductRepository;

import java.util.Set;

public class ProductService {
    private final ProductRepository productRepository;
    private final CompanyService companyService;

    public ProductService(ProductRepository productRepository, CompanyService companyService) {
        this.productRepository = productRepository;
        this.companyService = companyService;
    }

    public Product  addProduct(String name, double price, String companyName) {
        Company company = getCompanyByName(companyName);
        Product product = new Product(name, price, 0, company);
        companyService.addProduct(companyName,product);

        return productRepository.addProduct(product);

    }

    public Set<Product> findAll() {
        return productRepository.findAll();
    }


    private Company getCompanyByName(String companyName) {
        Company company = companyService.getCompanyByName(companyName);
        if (company == null) {
            throw new IllegalArgumentException("Company not found");
        }

        return company;
    }

    public void decreaseStock(Product product, int quantity) {
        productRepository.decreaseStock(product, quantity);
    }
}

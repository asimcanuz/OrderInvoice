package org.asodev.repository;

import org.asodev.model.Company;
import org.asodev.model.Product;

import java.util.HashSet;
import java.util.Set;

public class CompanyRepository {
    private final Set<Company> companies = new HashSet<>();

    public void save(Company company) {
        companies.add(company);
    }

    public Set<Company> getAll() {
        return companies;
    }

    public Company getCompanyByName(String name) {
        return companies.stream()
                .filter(company -> company.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Company company, Product product) {
        companies.stream()
                .filter(c -> c.getName().equals(company.getName()))
                .findFirst().ifPresent(selectedCompany -> selectedCompany.getProductList().add(product));

    }


}

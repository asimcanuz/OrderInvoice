package org.asodev.services;

import org.asodev.model.Company;
import org.asodev.model.Customer;
import org.asodev.model.Product;
import org.asodev.repository.CompanyRepository;
import org.asodev.repository.CustomerRepository;

import java.util.Set;

public class CompanyService {

    /**
     * Creates a new instance of CompanyService
     */
    private CompanyRepository companyRepository;

    /**
     * Creates a new instance of CompanyService
     * @param companyRepository
     */
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Creates new company
     * @param name
     * @param address
     * @param phone
     * @param email
     * @return void
     **/
    public Company save(String name, String address, String phone, String email){
        Company company = new Company(name, address, phone, email);
        companyRepository.save(company);
        return company;
    }

    /**
     * Find all companies
     * @return Set<Company>
     */
    public Set<Company> findAll(){
        return companyRepository.getAll();
    }

    /**
     * findCompanyByName
     * Find company by name
     * @param name
     * @return Customer
     */
    public Company getCompanyByName(String name){
        return companyRepository.getCompanyByName(name);
    }

    public void addProduct(String companyName, Product product){
        Company company = getCompanyByName(companyName);
        companyRepository.addProduct(company, product);
    }




}

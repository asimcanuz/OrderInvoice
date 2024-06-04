package org.asodev.services;

import org.asodev.model.Customer;
import org.asodev.repository.CustomerRepository;

import java.util.List;
import java.util.Set;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(String name, String address, String phone, String email) {
        Customer customer = new Customer(name, address, phone, email);
        customerRepository.save(customer);
        return customer;
    }

    public Set<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    public List<Customer> getCustomersByCharacter(String character) {
        return customerRepository.filterByCharacter(character);
    }
    public List<Customer> getCustomersByMonth(int month) {
        return customerRepository.filterByMonth(month);
    }

}

package org.asodev.repository;

import org.asodev.model.Customer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerRepository {

    /**
     * Creates a new instance of CustomerRepository
     */
    private final Set<Customer> customers = new HashSet<>();

    /**
     * Save a new customer
     *
     * @param customer
     * @return void
     */
    public Customer save(Customer customer){
        customers.add(customer);
        return customer;
    }

    /**
     * Find all customers
     *
     * @return Set<Customer>
     */
    public Set<Customer> findAll(){
        return customers;
    }


    /**
     * Filter customers by character
     * @param character
     * @return Customer
     */
    public List<Customer> filterByCharacter(String character){
        return customers.stream()
                .filter(customer -> customer.getName().contains(character))
                .collect(Collectors.toList());
    }


    /**
     * findCustomerByName
     * Find customer by name
     * @param name
     * @return Customer
     */

    public Customer findCustomerByName(String name) {
        return customers.stream()
                .filter(customer -> customer.getName().equals(name))
                .findFirst()
                .orElse(null);

    }

    /**
     * Filter customers by month
     * @param month
     * @return Customer
     */

    public List<Customer> filterByMonth(int month) {
        return customers.stream()
                .filter(customer -> customer.getCreatedDate().getMonthValue() == month)
                .collect(Collectors.toList());
    }


}

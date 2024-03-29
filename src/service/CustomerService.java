package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    final public static List<Customer> customers = new ArrayList<Customer>();

    //method that is adding customers
    final public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
    }

    //method getting the customer via email
    final public static Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if (customer.email.equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    //method getting all customers
    final public Collection<Customer> getAllCustomers() {
        return customers;
    }

}

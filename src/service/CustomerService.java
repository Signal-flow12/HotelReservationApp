package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    public static List<Customer> customers = new ArrayList<Customer>();

    //method that is adding customers
    public void addCustomer(String firstName,String lastName, String email){
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
    }

    //method getting the customer via email
    public static Customer getCustomer(String customerEmail){
        for (Customer customer : customers){
            if (customer.email.equals(customerEmail)){
                return customer;
            }
        }
        return null;
    }
    //method getting all customers
    public Collection<Customer> getAllCustomers() {
            return customers;
    }

}

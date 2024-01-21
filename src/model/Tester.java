package model;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("Thomas", "Craven", "Tcraveny@gmail.com");
        System.out.println(customer);
        Customer customer1 = new Customer("new", "Customer", "email");
        System.out.println(customer1);
    }

}

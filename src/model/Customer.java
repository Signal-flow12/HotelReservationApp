package model;

import java.util.regex.Pattern;

public class Customer {

    public String firstName;
    public String lastName;
    public String email;

    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstName, String lastName, String email){
        String errorMessage = validateEmail(email);
        if (errorMessage != null) {
            throw new IllegalArgumentException("Error: " + errorMessage);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }
    @Override
    public String toString(){
        return "\nFirst name: " + firstName +  "\nLast name: " + lastName + " \nEmail: " + email;
    }

    // Validate email format and return an error message if invalid
    public static String validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            return "Invalid email address format";
        }

        return null; // Email is valid
    }
}

package model;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("Thomas", "Craven", "Tcraveny@gmail.com");
        System.out.println(customer);

//        Customer customer2 = new Customer("Thomas", "Craven", "Tcravenygmail.com");
//        System.out.println(customer2);

        Room room = new Room("101", 10.0, RoomTypeEnum.SINGLE);
        System.out.println(room);
    }

}

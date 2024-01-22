package model;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("Thomas", "Craven", "Tcraveny@gmail.com");
        System.out.println(customer);

        Room room = new Room("101", 10.0, RoomTypeEnum.SINGLE);
        System.out.println(room);
    }

}

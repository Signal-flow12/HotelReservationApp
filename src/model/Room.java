package model;

public class Room implements RoomInterface {
    private String roomNumber;
    private Double price;
    private RoomTypeEnum roomType;

    public Room(String roomNumber, Double price, RoomTypeEnum roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public Double getRoomPrice() {
        return price;
    }

    public void setRoomPrice(Double price){
        this.price = price;
    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeEnum roomType){
        this.roomType = roomType;
    }

    public boolean isFree() {
        return false;
    }
    @Override
    public String toString(){
            return "Room{" +
                    "roomNumber='" + roomNumber + '\'' +
                    ", price=" + price +
                    ", roomType=" + roomType +
                    '}';
    }
}

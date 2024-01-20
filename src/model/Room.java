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

    @Override
    public String getRoomNumber() {
        return null;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public RoomTypeEnum getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }
}

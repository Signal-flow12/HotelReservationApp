package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, Double price, RoomTypeEnum roomType) {
        super(roomNumber, 0.0, roomType);
    }
    @Override
    public String toString(){
        return "Room price: " + getRoomPrice();
    }

}

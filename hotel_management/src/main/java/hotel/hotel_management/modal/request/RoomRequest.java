package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.hotel.Room;
import lombok.Data;

@Data
public class RoomRequest {
    private String name;
    private int floorId;
    private String roomType;
    private String pricePerDay;
    private String pricePerNight;
    private String priceFirstHour;
    private String pricePerHour;

    public Room setRoom(){
        Room room = new Room();
        room.setName(name);
        room.setRoomType(roomType);
        room.setPricePerDay(Double.parseDouble(pricePerDay));
        room.setPricePerNight(Double.parseDouble(pricePerNight));
        room.setPriceFirstHour(Double.parseDouble(priceFirstHour));
        room.setPricePerHour(Double.parseDouble(pricePerHour));
        room.setStatus(Room.Status.NULLABLE);
        return room;
    }
}

package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.Room;
import lombok.Data;

@Data
public class RoomRequest {
    private int floorId;
    private String name;
    private String type;
    private int priceFirstHour;
    private int priceNextHour;
    private int priceDay;
    private int priceNight;

    public Room addRoom() {
        Room room = new Room();
        room.setName(name);
        room.setType(type);
        room.setPriceFirstHour(priceFirstHour);
        room.setPriceNextHour(priceNextHour);
        room.setPriceDay(priceDay);
        room.setPriceNight(priceNight);
        room.setStatus(Room.StatusRoom.AVAILABLE);
        return room;
    }

    public void updateRoom(Room room) {
        room.setName(name);
        room.setType(type);
        room.setPriceFirstHour(priceFirstHour);
        room.setPriceNextHour(priceNextHour);
        room.setPriceDay(priceDay);
        room.setPriceNight(priceNight);
        room.setStatus(Room.StatusRoom.AVAILABLE);
    }
}

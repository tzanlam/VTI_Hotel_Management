package hotel.hotel_management.modal.response.Hotel;

import hotel.hotel_management.modal.entity.hotel.Room;
import lombok.Data;

@Data
public class RoomDTO {
    private int id;
    private String name;
    private int floorId;
    private int hotelId;
    private String roomType;
    private String note;
    private String typeCheckIn;
    private int priceDay;
    private int priceNight;
    private int priceFirstHour;
    private int pricePerHour;
    private String status;

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floorId = room.getFloor().getId();
        this.hotelId = room.getFloor().getHotel().getId();
        this.roomType = room.getRoomType();
        this.priceDay = (int) room.getPricePerDay();
        this.priceNight = (int) room.getPricePerNight();
        this.priceFirstHour = (int) room.getPriceFirstHour();
        this.pricePerHour = (int) room.getPricePerHour();
        this.status = room.getStatus().name();
    }
}

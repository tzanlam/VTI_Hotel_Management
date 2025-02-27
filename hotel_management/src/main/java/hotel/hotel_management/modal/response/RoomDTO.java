package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Room;
import lombok.Data;

@Data
public class RoomDTO {
    private int id;
    private String name;
    private String type;
    private String priceFirstHour;
    private String priceNextHour;
    private String priceDay;
    private String priceNight;
    private String status;

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.type = room.getType();
        this.priceFirstHour = String.valueOf(room.getPriceFirstHour());
        this.priceNextHour = String.valueOf(room.getPriceNextHour());
        this.priceDay = String.valueOf(room.getPriceDay());
        this.priceNight = String.valueOf(room.getPriceNight());
        this.status = String.valueOf(room.getStatus());
    }
}

package hotel.hotel_management.modal.response.Hotel;

import hotel.hotel_management.modal.entity.hotel.Floor;
import hotel.hotel_management.modal.entity.hotel.Room;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FloorDTO {
    private int id;
    private String name;
    private int hotelId;
    private List<Integer> roomIds;
    private String status;

    public FloorDTO(Floor floor) {
        this.id = floor.getId();
        this.name = floor.getName();
        this.hotelId = floor.getHotel().getId();
        if (floor.getRooms() != null) {
            this.roomIds = floor.getRooms().stream().map(Room::getId).collect(Collectors.toList());
        }else {
            this.roomIds = new ArrayList<>();
        }
        this.status = floor.getStatus().toString();
    }
}

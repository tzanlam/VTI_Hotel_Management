package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Floor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FloorDTO {
    private int id;
    private String name;
    private int hotelId;
    private List<RoomDTO> rooms;
    private String status;
    public FloorDTO(Floor floor) {
        this.id = floor.getId();
        this.name = floor.getName();
        this.hotelId = floor.getHotel().getId();
        this.rooms = floor.getRooms() != null ?
                floor.getRooms().stream().map(RoomDTO::new).collect(Collectors.toList()) : new ArrayList<>();
        this.status = String.valueOf(floor.getStatus());
    }
}

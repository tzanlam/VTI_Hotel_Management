package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Hotel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class HotelDTO {
    private int id;
    private String name;
    private String image;
    private String address;
//    private List<Integer> accountIds;
    private List<FloorDTO> floors;
    private String status;

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.image = hotel.getImage();
        this.address = hotel.getAddress();
        this.floors =
                hotel.getFloors() == null ?
                        new ArrayList<>() :
                        new ArrayList<>(hotel.getFloors().stream()
                                .map(FloorDTO::new).collect(Collectors.toList()));
        this.status = hotel.getStatus().toString();
    }
}

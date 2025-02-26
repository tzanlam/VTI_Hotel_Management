package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Hotel;
import lombok.Data;

@Data
public class HotelDTO {
    private int id;

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
    }
}

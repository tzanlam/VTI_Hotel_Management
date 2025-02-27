package hotel.hotel_management.modal.request;

import lombok.Data;

@Data
public class FloorRequest {
    private int id;
    private String name;
    private int hotelId;
}

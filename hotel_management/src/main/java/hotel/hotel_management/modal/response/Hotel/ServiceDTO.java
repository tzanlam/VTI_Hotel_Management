package hotel.hotel_management.modal.response.Hotel;

import hotel.hotel_management.modal.entity.hotel.Service;
import lombok.Data;

@Data
public class ServiceDTO {
    private int id;
    private String name;
    private int price;
    private int hotelId;

    public ServiceDTO(Service service) {
        this.id = service.getId();
        this.name = service.getName();
        this.price = (int) service.getPrice();
        this.hotelId = service.getHotel().getId();
    }
}

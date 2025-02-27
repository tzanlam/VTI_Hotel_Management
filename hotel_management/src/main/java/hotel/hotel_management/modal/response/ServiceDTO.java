package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Service;
import lombok.Data;

@Data
public class ServiceDTO {
    private int id;
    private String type;
    private String name;
    private String price;
    private int hotelId;
    private int quantity;
    private String status;

    public ServiceDTO(Service service) {
        this.id = service.getId();
        this.type = service.getType();
        this.name = service.getName();
        this.price = String.valueOf(service.getPrice());
        this.hotelId = service.getHotel().getId();
        this.quantity = service.getQuantity();
        this.status = String.valueOf(service.getStatus());
    }
}

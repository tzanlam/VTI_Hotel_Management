package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.constant.StatusOL;
import hotel.hotel_management.modal.entity.Service;
import lombok.Data;

@Data
public class ServiceRequest {
    private String name;
    private String type;
    private int price;
    private int quantity;
    private int hotelId;

    public Service addService(){
        Service service = new Service();
        service.setName(name);
        service.setType(type);
        service.setPrice(price);
        service.setQuantity(quantity);
        service.setStatus(StatusOL.OPEN);
        return service;
    }

    public void updateService(Service service){
        service.setName(name);
        service.setType(type);
        service.setPrice(price);
        service.setQuantity(quantity);
        service.setStatus(StatusOL.OPEN);
    }
}

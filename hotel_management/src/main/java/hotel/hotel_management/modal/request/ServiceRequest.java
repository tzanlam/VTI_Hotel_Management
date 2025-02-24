package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.hotel.Service;
import lombok.Data;

@Data
public class ServiceRequest {
    private String name;
    private String price;
    private int hotelId;

    public Service setService() {
        Service service = new Service();
        service.setName(name);
        service.setPrice(Double.parseDouble(price));
        return service;
    }

    public void updateService(Service service) {
        service.setName(name);
        service.setPrice(Double.parseDouble(price));
        service.setHotel(service.getHotel());
    }
}

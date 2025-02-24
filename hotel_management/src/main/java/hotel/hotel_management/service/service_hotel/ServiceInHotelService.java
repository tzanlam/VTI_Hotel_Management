package hotel.hotel_management.service.service_hotel;

import hotel.hotel_management.modal.entity.hotel.Service;
import hotel.hotel_management.modal.request.ServiceRequest;
import hotel.hotel_management.modal.response.Hotel.ServiceDTO;

import java.util.List;

public interface ServiceInHotelService {
    // get
    List<ServiceDTO> findAllServiceByHotelId(int hotelId);
    ServiceDTO findServiceById(int id);

    // create
    ServiceDTO createNewService(ServiceRequest request);

    // update
    ServiceDTO updateService(ServiceRequest request, int id);


}

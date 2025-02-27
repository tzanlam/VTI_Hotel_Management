package hotel.hotel_management.service.service;

import hotel.hotel_management.modal.request.ServiceRequest;
import hotel.hotel_management.modal.response.ServiceDTO;

import java.util.List;

public interface ServiceService {
    // get
    List<ServiceDTO> findByHotelId(int hotelId);

    // post
    ServiceDTO createNewService(ServiceRequest request);

    // put
    ServiceDTO updateService(ServiceRequest request, int id);

    // delete
    ServiceDTO deleteService(int id);
}

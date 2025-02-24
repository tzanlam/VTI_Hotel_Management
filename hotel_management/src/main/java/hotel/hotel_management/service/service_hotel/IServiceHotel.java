package hotel.hotel_management.service.service_hotel;

import hotel.hotel_management.modal.entity.hotel.Hotel;
import hotel.hotel_management.modal.request.ServiceRequest;
import hotel.hotel_management.modal.response.Hotel.ServiceDTO;
import hotel.hotel_management.repository.HotelRepository;
import hotel.hotel_management.repository.ServiceRepository;
import hotel.hotel_management.modal.entity.hotel.Service;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class IServiceHotel implements ServiceInHotelService{
    private final ServiceRepository serviceRepository;
    private final HotelRepository hotelRepository;

    public IServiceHotel(ServiceRepository serviceRepository, HotelRepository hotelRepository) {
        this.serviceRepository = serviceRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<ServiceDTO> findAllServiceByHotelId(int hotelId) {
        return serviceRepository.findByHotelId(hotelId).stream()
                .map(ServiceDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDTO findServiceById(int id) {
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Service not found")
        );
        return new ServiceDTO(service);
    }

    @Override
    public ServiceDTO createNewService(ServiceRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(
                () -> new RuntimeException("Hotel not found")
        );
        Service service = request.setService();
        service.setHotel(hotel);
        serviceRepository.save(service);
        return new ServiceDTO(service);
    }

    @Override
    public ServiceDTO updateService(ServiceRequest request, int id) {
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Service not found")
        );
        request.updateService(service);
        serviceRepository.save(service);
        return new ServiceDTO(service);
    }
}

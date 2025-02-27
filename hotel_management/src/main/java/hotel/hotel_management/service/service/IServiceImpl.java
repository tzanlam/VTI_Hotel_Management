package hotel.hotel_management.service.service;

import hotel.hotel_management.modal.constant.StatusOL;
import hotel.hotel_management.modal.entity.Hotel;
import hotel.hotel_management.modal.request.ServiceRequest;
import hotel.hotel_management.modal.response.ServiceDTO;
import hotel.hotel_management.repository.HotelRepository;
import hotel.hotel_management.repository.ServiceRepository;
import hotel.hotel_management.modal.entity.Service;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class IServiceImpl implements ServiceService{
    private final ServiceRepository serviceRepository;
    private final HotelRepository hotelRepository;

    public IServiceImpl(ServiceRepository serviceRepository, HotelRepository hotelRepository) {
        this.serviceRepository = serviceRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<ServiceDTO> findByHotelId(int hotelId) {
        return serviceRepository.findServiceByHotelId(hotelId).stream().map(ServiceDTO::new).collect(Collectors.toList());
    }

    @Override
    public ServiceDTO createNewService(ServiceRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(
                () -> new IllegalArgumentException("Hotel not found")
        );
        Service service = request.addService();
        service.setHotel(hotel);
        serviceRepository.save(service);
        return new ServiceDTO(service);
    }

    @Override
    public ServiceDTO updateService(ServiceRequest request, int id) {
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Service not found")
        );
        request.updateService(service);
        serviceRepository.save(service);
        return new ServiceDTO(service);
    }

    @Override
    public ServiceDTO deleteService(int id) {
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Service not found")
        );
        if (service.getStatus() == StatusOL.OPEN) {
            service.setStatus(StatusOL.CLOSED);
        }else {
            service.setStatus(StatusOL.OPEN);
        }
        serviceRepository.save(service);
        return new ServiceDTO(service);
    }
}

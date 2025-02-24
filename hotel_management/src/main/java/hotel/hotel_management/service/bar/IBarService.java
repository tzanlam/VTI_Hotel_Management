package hotel.hotel_management.service.bar;

import hotel.hotel_management.modal.entity.hotel.Bar;
import hotel.hotel_management.modal.entity.hotel.Hotel;
import hotel.hotel_management.modal.request.BarRequest;
import hotel.hotel_management.modal.response.Hotel.BarDTO;
import hotel.hotel_management.repository.BarRepository;
import hotel.hotel_management.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IBarService implements BarService{
    private final BarRepository barRepository;
    private final HotelRepository hotelRepository;

    public IBarService(BarRepository barRepository, HotelRepository hotelRepository) {
        this.barRepository = barRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<BarDTO> getBars() {
        return barRepository.findAll().stream()
                .map(BarDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public BarDTO addBar(BarRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(
                ()-> new IllegalArgumentException("Hotel not found"));
        Bar bar = request.setBar();
        bar.setHotel(hotel);
        barRepository.save(bar);
        return new BarDTO(bar);
    }

    @Override
    public BarDTO updateBar(BarRequest request, int id) {
        Bar bar = barRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Bar not found")
        );
        bar.setHotel(bar.getHotel());
        bar.setName(request.getName());
        bar.setPrice(request.getPrice());
        barRepository.save(bar);
        return new BarDTO(bar);
    }

    @Override
    public BarDTO hideOpenBar(int id) {
        Bar bar = barRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Bar not found")
        );
        if (bar.getStatus() == Bar.BarStatus.ACTIVE){
            bar.setStatus(Bar.BarStatus.INACTIVE);
        }else {
            bar.setStatus(Bar.BarStatus.ACTIVE);
        }
        barRepository.save(bar);
        return new BarDTO(bar);
    }
}

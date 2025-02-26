package hotel.hotel_management.service.hotel;

import hotel.hotel_management.modal.constant.StatusOL;
import hotel.hotel_management.modal.entity.Hotel;
import hotel.hotel_management.modal.request.HotelRequest;
import hotel.hotel_management.modal.response.AccountDTO;
import hotel.hotel_management.modal.response.HotelDTO;
import hotel.hotel_management.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IHotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    public IHotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelDTO> getHotels() {
        return hotelRepository.findAll().stream()
                .map(HotelDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> getByHotelId(int hotelId) {
        return hotelRepository.findByHotelId(hotelId).stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO getHotelById(int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Hotel not found")
        );
        return new HotelDTO(hotel);
    }

    @Override
    public HotelDTO createHotel(HotelRequest request) {
        Hotel hotel = request.addHotel();
        hotelRepository.save(hotel);
        return new HotelDTO(hotel);
    }

    @Override
    public HotelDTO updateHotel(HotelRequest request, int hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new NullPointerException("Hotel not found")
        );
        request.updateHotel(hotel);
        hotelRepository.save(hotel);
        return new HotelDTO(hotel);
    }

    @Override
    public HotelDTO deleteHotel(int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Hotel not found")
        );
        if (hotel.getStatus() == StatusOL.OPEN) {
            hotel.setStatus(StatusOL.CLOSED);
        } else {
            hotel.setStatus(StatusOL.OPEN);
        }
        return new HotelDTO(hotel);
    }
}

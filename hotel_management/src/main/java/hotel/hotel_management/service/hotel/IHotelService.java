package hotel.hotel_management.service.hotel;

import hotel.hotel_management.modal.entity.hotel.Account;
import hotel.hotel_management.modal.entity.hotel.Hotel;
import hotel.hotel_management.modal.request.HotelRequest;
import hotel.hotel_management.modal.response.Hotel.HotelDTO;
import hotel.hotel_management.repository.AccountRepository;
import hotel.hotel_management.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IHotelService implements HotelService{
    private final HotelRepository hotelRepository;
    private final AccountRepository accountRepository;

    public IHotelService(HotelRepository hotelRepository, AccountRepository accountRepository) {
        this.hotelRepository = hotelRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<HotelDTO> findHotelByAccount(int accountId) {
        List<Hotel> hotels = hotelRepository.findByAccountId(accountId);
        return hotels.stream()
                .map(HotelDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO findHotelById(int id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        assert hotel != null;
        return new HotelDTO(hotel);
    }

    @Override
    public HotelDTO createHotel(HotelRequest request, int hotelierId) throws Exception {
        Account account = accountRepository.findById(hotelierId).orElseThrow(
                () -> new Exception("Account not found")
        );

        if (!accountRepository.isHotelier(hotelierId)) {
            throw new Exception("You don't have authorization");
        }
        Hotel hotel = request.createNewHotel();
        if (hotel.getAccounts() == null) {
            hotel.setAccounts(new ArrayList<>());
        }
        hotel.getAccounts().add(account);

        hotelRepository.save(hotel);
        return new HotelDTO(hotel);
    }

    @Override
    public HotelDTO updateHotel(int id, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        assert hotel != null;
        request.modifyHotel(hotel);
        hotelRepository.save(hotel);
        return new HotelDTO(hotel);
    }

    @Override
    public HotelDTO changeStatusHotel(int id) throws Exception {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                ()->new Exception("Hotel not found"));
        if (hotel.getStatus().equals(Hotel.HotelStatus.OPEN)){
            hotel.setStatus(Hotel.HotelStatus.CLOSED);
            hotelRepository.save(hotel);
        }else {
            hotel.setStatus(Hotel.HotelStatus.OPEN);
            hotelRepository.save(hotel);
        }
        return new HotelDTO(hotel);
    }
}

package hotel.hotel_management.service.guest;

import hotel.hotel_management.modal.entity.Guest;
import hotel.hotel_management.modal.request.GuestRequest;
import hotel.hotel_management.modal.response.GuestDTO;
import hotel.hotel_management.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IGuestService implements GuestService{
    private final GuestRepository guestRepository;

    public IGuestService( GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<GuestDTO> getAllGuestsByHotelId(int hotelId) {
        return guestRepository.findByHotelId(hotelId).stream().map(GuestDTO::new).collect(Collectors.toList());
    }

    @Override
    public GuestDTO getById(int id) {
        Guest guest = guestRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Guest not found")
        );
        return new GuestDTO(guest);
    }

    @Override
    public GuestDTO createGuest(GuestRequest request) throws Exception {
        Guest guest = request.addGuest();
        guestRepository.save(guest);
        return new GuestDTO(guest);
    }

    @Override
    public GuestDTO updateGuest(GuestRequest request, int id) throws Exception {
        Guest guest = guestRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Guest not found")
        );
        request.updateGuest(guest);
        guestRepository.save(guest);
        return new GuestDTO(guest);
    }

    @Override
    public String deleteGuest(int id) {
        Guest guest = guestRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Guest not found")
        );
        if (guest.getCountAmount() != 0 || guest.getCountStay() != 0){
            throw new RuntimeException("Guest count amount is correct");
        }
        guestRepository.delete(guest);
        return "deleted";
    }

    @Override
    public GuestDTO changeStatus(int id, int status) {
        Guest guest = guestRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Guest not found")
        );
        switch (status){
            case 1:
                guest.setType(Guest.TypeGuest.REGULAR_GUEST);
                break;
            case 2:
                guest.setType(Guest.TypeGuest.BAD_GUEST);
                break;
            case 3:
                guest.setType(Guest.TypeGuest.EMPLOYEE_GUEST);
                break;
        }
        guestRepository.save(guest);
        return new GuestDTO(guest);
    }
}

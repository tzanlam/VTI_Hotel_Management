package hotel.hotel_management.service.guest;

import hotel.hotel_management.modal.request.GuestRequest;
import hotel.hotel_management.modal.response.GuestDTO;

import java.util.List;

public interface GuestService {
    // get
    List<GuestDTO> getAllGuestsByHotelId(int hotelId);
    GuestDTO getById(int id);

    // post
    GuestDTO createGuest(GuestRequest request) throws Exception;

    // put
    GuestDTO updateGuest(GuestRequest request, int id) throws Exception;

    // delete
    String deleteGuest(int id);

    // make type
    GuestDTO changeStatus(int id, int status);
}

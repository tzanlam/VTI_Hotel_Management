package hotel.hotel_management.service.hotel;

import hotel.hotel_management.modal.request.HotelRequest;
import hotel.hotel_management.modal.response.AccountDTO;
import hotel.hotel_management.modal.response.HotelDTO;

import java.util.List;

public interface HotelService {
    // get
    List<HotelDTO> getHotels();
    List<AccountDTO> getByHotelId(int hotelId);
    HotelDTO getHotelById(int id);

    // post
    HotelDTO createHotel(HotelRequest request);

    // put
    HotelDTO updateHotel(HotelRequest request, int hotelId);

    // delete
    HotelDTO deleteHotel(int id);
}

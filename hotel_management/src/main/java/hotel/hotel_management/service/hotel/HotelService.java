package hotel.hotel_management.service.hotel;

import hotel.hotel_management.modal.request.HotelRequest;
import hotel.hotel_management.modal.response.Hotel.HotelDTO;

import java.util.List;

public interface HotelService {
    // find
    List<HotelDTO> findHotelByAccount(int accountId);
    HotelDTO findHotelById(int id);

    // create
    HotelDTO createHotel(HotelRequest request, int hotelierId) throws Exception;

    // update
    HotelDTO updateHotel(int id, HotelRequest request);

    // close-open hotel
    HotelDTO changeStatusHotel(int id) throws Exception;
}

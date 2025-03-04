package hotel.hotel_management.service.booked;

import hotel.hotel_management.modal.request.BookedRequest;
import hotel.hotel_management.modal.response.BookedDTO;

import java.util.List;

public interface BookedService {
    // get
    List<BookedDTO> getBookedByHotel(int hotelId);
    BookedDTO getById(int bookedId);
    List<BookedDTO> searchBySpec(String spec, String... fields);

    // post
    BookedDTO createBooked(BookedRequest request, boolean isBooked);

    // put
    BookedDTO updateBooked(int bookedId, BookedRequest request);

    // delete
    String deleteBooked(int bookedId);
}

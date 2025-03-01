package hotel.hotel_management.service.bookedHistory;

import hotel.hotel_management.modal.response.BookedHistoryDTO;

import java.util.List;

public interface BookedHistoryService {
    // get
    List<BookedHistoryDTO> findHistories();
    BookedHistoryDTO findBookedHistoryById(int id);
    List<BookedHistoryDTO> findByHotelId(int hotelId);

    // post
    BookedHistoryDTO actionByEmployee(int bookedId, int accountId);
}

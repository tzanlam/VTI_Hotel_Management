package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.BookedHistory;
import lombok.Data;

@Data
public class BookedHistoryDTO {
    private int id;
    private int bookedId;
    private int accountId;
    private String action;

    public BookedHistoryDTO(BookedHistory bookedHistory) {
        this.id = bookedHistory.getId();
        this.bookedId = bookedHistory.getBooked().getId();
        this.accountId = bookedHistory.getAccount().getId();
        this.action = String.valueOf(bookedHistory.getAction());
    }
}

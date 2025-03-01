package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.BookedHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedHistoryRepository extends JpaRepository<BookedHistory, Integer> {
    @Query("select bh from BookedHistory bh join Booked b join Room r join Floor f join Hotel h where h.id = :hotelId")
    List<BookedHistory> findByHotelId(int hotelId);
}

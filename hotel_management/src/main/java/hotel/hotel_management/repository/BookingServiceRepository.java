package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.guest.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingServiceRepository extends JpaRepository<BookingService, Integer> {
}

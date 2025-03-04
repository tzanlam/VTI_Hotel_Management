package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query(
            "select p from Payment p join Booked b join Room r join Floor f join Hotel h where h.id = :hotelId"
    )
    List<Payment> findByHotelId(@Param("hotelId") int hotelId);
    @Query(
            "select p from Payment p join Account a where a.id = :accountId"
    )
    List<Payment> findByAccountId(@Param("accountId") int accountId);
}

package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Account;
import hotel.hotel_management.modal.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT h FROM Hotel h JOIN h.accounts a WHERE a.id = :accountId")
    List<Hotel> findByAccountId(@Param("accountId") int accountId);

    @Query("select a from Account a join a.hotels h where h.id = :hotelId")
    List<Account> findByHotelId(@Param("hotelId") int hotelId);
}

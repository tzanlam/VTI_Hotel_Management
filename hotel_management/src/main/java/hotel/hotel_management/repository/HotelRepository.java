package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("select h from Hotel h join h.accounts a where a.id = :accountId")
    List<Hotel> findByAccountId(@Param("accountId") int accountId);
}

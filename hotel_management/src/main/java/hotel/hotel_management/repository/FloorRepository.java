package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {
    @Query("select f from Floor f join Hotel h where h.id = :hotelId")
    List<Floor> findByHotelId(@Param("hotelId") int hotelId);
}

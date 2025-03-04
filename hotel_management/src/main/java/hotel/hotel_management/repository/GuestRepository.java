package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    @Query("select g from Guest g join Hotel h where h.id = :hotelId")
    List<Guest> findByHotelId(@Param("hotelId")int hotelId);

    @Query("select g from Guest g where g.fullName = :fullName")
    Optional<Guest> findByFullName(@Param("fullName")String fullName);
}

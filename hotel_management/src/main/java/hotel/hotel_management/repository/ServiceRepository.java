package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("select s from Service s join Hotel h where h.id = :hotelId")
    List<Service> findServiceByHotelId(@Param("hotelId") int hotelId);
}

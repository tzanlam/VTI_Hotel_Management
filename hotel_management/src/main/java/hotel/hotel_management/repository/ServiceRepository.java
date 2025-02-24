package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.hotel.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("select s from Service s where s.hotel.id = :hotelId")
    List<Service> findByHotelId(int hotelId);
}

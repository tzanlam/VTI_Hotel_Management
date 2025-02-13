package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.hotel.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {
}

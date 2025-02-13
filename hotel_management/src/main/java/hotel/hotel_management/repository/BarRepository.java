package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.hotel.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepository extends JpaRepository<Bar, Integer> {
}

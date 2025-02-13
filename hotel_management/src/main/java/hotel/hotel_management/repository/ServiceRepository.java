package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.hotel.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}

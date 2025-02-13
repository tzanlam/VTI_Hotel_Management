package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.guest.Booked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedRepository extends JpaRepository<Booked, Integer> {
}

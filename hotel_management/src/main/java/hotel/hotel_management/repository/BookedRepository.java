package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Booked;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedRepository extends JpaRepository<Booked, Integer>, JpaSpecificationExecutor<Booked> {
    @Query(
            "SELECT DISTINCT b FROM Booked b " +
                    "JOIN b.room r " +
                    "JOIN r. floor f " +
                    "join f.hotel h where h.id = :hotelId"
    )
    List<Booked> findByHotelId(@Param("hotelId") int hotelId);
}

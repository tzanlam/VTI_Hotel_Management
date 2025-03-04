package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Booked;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedRepository extends JpaRepository<Booked, Integer> {
    @Query(
            "SELECT DISTINCT b FROM Booked b " +
                    "JOIN b.room r " +
                    "JOIN r. floor f " +
                    "join f.hotel h where h.id = :hotelId"
    )
    List<Booked> findByHotelId(@Param("hotelId") int hotelId);

    @Query("SELECT b FROM Booked b WHERE " +
            "LOWER(b.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(b.totalPrice AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "CAST(b.id AS string) LIKE CONCAT('%', :keyword, '%')")
    List<Booked> findAll(Specification<Booked> combinedSpec, Sort sort);
}

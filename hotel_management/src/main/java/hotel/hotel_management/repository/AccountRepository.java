package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.hotel.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a JOIN a.hotels h WHERE h.id = :hotelId")
    List<Account> findByHotelId(@Param("hotelId") int hotelId);

    Account findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select case when a.position = 'HOTELIER' then true else false end from Account a where a.id = :accountId")
    boolean isHotelier(@Param("accountId") int accountId);

}

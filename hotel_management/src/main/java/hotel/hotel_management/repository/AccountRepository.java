package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM Account WHERE email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("select a from Account a where a.email = :email")
    Account findByEmail(@Param("email") String email);
}

package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("select i from Invoice i where i.hotel.id = :hotelId")
    List<Invoice> findByHotelId(int hotelId);

    @Query("select i from Invoice i where i.account.id = :accountId")
    List<Invoice> findByAccountId(int accountId);
}

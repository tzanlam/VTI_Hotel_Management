package hotel.hotel_management.modal.entity.guest;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "booked_id", referencedColumnName = "id")
    private Booked booked;

    @Column(name = "total_amount")
    private int total_amount;

    @Column(name = "description")
    private String description;

}

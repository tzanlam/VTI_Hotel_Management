package hotel.hotel_management.modal.entity.guest;

import hotel.hotel_management.modal.entity.hotel.Bar;
import hotel.hotel_management.modal.entity.hotel.Service;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "booking_service")
public class BookingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "booked_id", referencedColumnName = "id")
    private Booked booked;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "bar_id", referencedColumnName = "id")
    private Bar bar;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;
}

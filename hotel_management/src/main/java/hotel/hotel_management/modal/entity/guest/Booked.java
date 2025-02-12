package hotel.hotel_management.modal.entity.guest;

import hotel.hotel_management.modal.entity.hotel.Room;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booked")
public class Booked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusBooked status;

    public enum StatusBooked {
        LIVE,
        BOOKING,
        EXPIRE,
        EXPIRE_BOOKING
    }
}

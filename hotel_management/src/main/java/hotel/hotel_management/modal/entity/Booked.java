package hotel.hotel_management.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table
@Entity
public class Booked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable
    private List<Guest> guest = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<Room> room = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<Service> service = new ArrayList<>();

    @OneToMany
    private List<BookedHistory> histories = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<Payment> payments = new ArrayList<>();
    @Column
    @Enumerated(EnumType.STRING)
    private TypeCheckin typeCheckin;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeStay typeStay;

    @Column
    private LocalDateTime checkIn;

    @Column
    private LocalDateTime checkOut;

    @Column
    private double totalPrice;

    @Column
    private StatusBooked status;


    public enum StatusBooked {
        BOOKED,
        EXPIRED_BOOKED,
        STAY,
        EXPIRED_STAY,
        CANCELLED,
    }
    public enum TypeStay {
        HOURLY, DAILY, NIGHTLY
    }

    public enum TypeCheckin {
        WALK_IN,
        ZALO,
        FACEBOOK,
        GOOGLE,
        BOOKING,
        G2J,
        TRAVEL,
        PHONE_NUMBER
    }
}

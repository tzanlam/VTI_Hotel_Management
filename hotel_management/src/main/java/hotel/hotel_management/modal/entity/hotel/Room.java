package hotel.hotel_management.modal.entity.hotel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private Floor floor;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "note")
    private String note;

    @Column(name = "type_check_in")
    @Enumerated(EnumType.STRING)
    private TypeCheckin typeCheckIn;

    @Column(name = "price_per_day")
    private double pricePerDay;

    @Column(name = "price_per_night")
    private double pricePerNight;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        BOOKED,
        LIVED,
        NULLABLE,
        EXPIRE
    }

    public enum TypeCheckin {
        WALK_IN,
        FACEBOOK,
        GOOGLE,
        ZA_LO,
        PHONE,
        BOOKING,
        GO2JOY,
        TRAVEL
    }
}
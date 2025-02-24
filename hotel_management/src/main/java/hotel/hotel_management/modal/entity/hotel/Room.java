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

    @Column(name = "price_per_day")
    private double pricePerDay;

    @Column(name = "price_per_night")
    private double pricePerNight;

    @Column(name = "price_first_hour")
    private double priceFirstHour;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum Status {
        BOOKED,
        LIVED,
        NULLABLE,
        EXPIRE
    }
}
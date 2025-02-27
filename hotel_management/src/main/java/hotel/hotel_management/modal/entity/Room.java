package hotel.hotel_management.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String priceFirstHour;

    @Column
    private String priceNextHour;

    @Column
    private String priceDay;

    @Column
    private String priceNight;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusRoom status;

    public enum StatusRoom {
        AVAILABLE,
        FIXING,
        DELETED
    }
}

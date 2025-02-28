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
    private int priceFirstHour;

    @Column
    private int priceNextHour;

    @Column
    private int priceDay;

    @Column
    private int priceNight;

    @ManyToOne
    @JoinColumn
    private Floor floor;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusRoom status;

    public enum StatusRoom {
        AVAILABLE,
        FIXING,
        DELETED
    }
}

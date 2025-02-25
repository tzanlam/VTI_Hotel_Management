package hotel.hotel_management.modal.entity;

import hotel.hotel_management.modal.constant.StatusOL;
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
    private String priceFirstHouse;

    @Column
    private String priceNextHouse;

    @Column
    private String priceDay;

    @Column
    private String priceNight;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusOL status;
}

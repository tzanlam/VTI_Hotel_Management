package hotel.hotel_management.modal.entity;

import hotel.hotel_management.modal.constant.StatusOL;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String type;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private int quantity;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusOL status;
}

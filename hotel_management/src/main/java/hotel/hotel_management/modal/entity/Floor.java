package hotel.hotel_management.modal.entity;

import hotel.hotel_management.modal.constant.StatusOL;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private StatusOL status;
}

package hotel.hotel_management.modal.entity;

import hotel.hotel_management.modal.constant.StatusOL;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private String address;

    @ManyToMany(mappedBy = "hotels")
    private List<Account> accounts;

    @OneToMany(mappedBy = "hotels")
    private List<Floor> floors;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusOL status;
}

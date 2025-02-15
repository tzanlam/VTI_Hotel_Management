package hotel.hotel_management.modal.entity.hotel;

import hotel.hotel_management.modal.entity.Base;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "hotel")
@EqualsAndHashCode(callSuper = true)
public class Hotel extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private HotelStatus status;

    @ManyToMany(mappedBy = "hotels")
    private List<Account> accounts;

    public enum HotelStatus {
        OPEN,
        CLOSED,
    }
}

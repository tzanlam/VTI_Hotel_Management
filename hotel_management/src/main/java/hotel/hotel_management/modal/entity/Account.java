package hotel.hotel_management.modal.entity;

import hotel.hotel_management.modal.constant.StatusAction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper=true)
public class Account extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String password;

    @Column
    private String image;

    @Column
    private String fullName;

    @Column
    private String evaluate;

    @Column
    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToMany
    @JoinTable(
            name = "account_hotel",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<Hotel> hotels;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusAction status;

    @Column
    private String vertical;

    public enum Position {
        HOTELIER, MANAGER, RECEPTION
    }
}

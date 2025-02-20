package hotel.hotel_management.modal.entity.hotel;

import hotel.hotel_management.modal.entity.Base;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@Table(name = "`account`")
@EqualsAndHashCode(callSuper=true)
public class Account extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @ManyToMany
    @JoinTable(
            name = "account_hotel",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<Hotel> hotels;

    public enum AccountStatus {
        ACTIVE,
        PENDING,
        INACTIVE
    }
    public enum Position {
        RECEPTIONIST,
        MANAGER,
        HOTELIER
    }
}

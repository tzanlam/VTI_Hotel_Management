package hotel.hotel_management.modal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@EqualsAndHashCode(callSuper=true)
public class Payment extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "payments")
    private List<Booked> booked = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Account account;

    @Column
    private double amount;

    @Column
    @Enumerated(EnumType.STRING)
    private TypePayment typePayment;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public enum PaymentStatus {
        SUCCESS,
        NOT_CONFIRM
    }
    public enum TypePayment{
        CASH,
        BANKING,
        E_WALLET,
        DEBT
    }
}

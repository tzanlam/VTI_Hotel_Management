package hotel.hotel_management.modal.entity.guest;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private MethodPayment method;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPayment status;

    public enum MethodPayment {
        CASH,
        BANK,
        MOMO,
        DEBT
    }

    public enum StatusPayment {
        SUCCESS,
        WAITING,
        FAILED,
    }
}

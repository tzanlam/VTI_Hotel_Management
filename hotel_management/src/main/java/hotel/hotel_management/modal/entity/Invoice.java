package hotel.hotel_management.modal.entity;

import hotel.hotel_management.modal.constant.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
public class Invoice extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private double price;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Hotel hotel;

    @Column
    private String customerName;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPaymentInvoice status;

    public enum StatusPaymentInvoice {
        PAID,
        UNPAID
    }
}

package hotel.hotel_management.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class BookedHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    private Booked booked;

    @ManyToOne
    @JoinColumn
    private Account account;

    @Column
    private Action action;

    public enum Action {
        CHECKIN, CHECKOUT, PAYMENT, PAYMENT_CANCEL, CHANGE_TIME, ADD_SERVICE, BOOKING
    }
}

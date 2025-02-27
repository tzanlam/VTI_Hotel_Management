package hotel.hotel_management.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String fullName;

    @Column
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender Gender;

    @Column
    private LocalDate birthDate;

    @Column
    private String idCardNumber;

    @Column
    private String nation;

    @Column
    private String address;

    @Column
    private String image;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeGuest type;

    @Column
    private String note;

    @Column
    private int countStay;

    @Column
    private int countAmount;

    @ManyToOne
    @JoinColumn
    private Hotel hotel;

    public enum Gender {
        MALE, FEMALE,
    }

    public enum TypeGuest {
        REGULAR_GUEST,
        NEW_GUEST,
        BAD_GUEST,
        EMPLOYEE_GUEST,
    }
}

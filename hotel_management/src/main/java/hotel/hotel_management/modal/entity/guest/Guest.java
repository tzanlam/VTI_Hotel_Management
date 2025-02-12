package hotel.hotel_management.modal.entity.guest;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "image_card")
    private String imageCard;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "nation")
    private String nation;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeGuest type;

    @Column(name = "note")
    private String note;


    public enum TypeGuest {
        FAMILIAR_GUEST,
        NEW_GUEST,
        BACK_LIST
    }
}

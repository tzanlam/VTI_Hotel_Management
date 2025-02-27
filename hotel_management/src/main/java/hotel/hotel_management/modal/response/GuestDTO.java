package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Guest;
import lombok.Data;

@Data
public class GuestDTO {
    private int id;
    private String fullName;
    private String phone;
    private String gender;
    private String birthdate;
    private String idCardNumber;
    private String nation;
    private String address;
    private String image;
    private String typeGuest;
    private String note;

    public GuestDTO(Guest guest) {
        this.id = guest.getId();
        this.fullName = guest.getFullName();
        this.phone = guest.getPhone();
        this.gender = String.valueOf(guest.getGender());
        this.birthdate = String.valueOf(guest.getBirthDate());
        this.idCardNumber = guest.getIdCardNumber();
        this.nation = guest.getNation();
        this.address = guest.getAddress();
        this.image = guest.getImage();
        this.typeGuest = String.valueOf(guest.getGender());
        this.note = guest.getNote();
    }
}

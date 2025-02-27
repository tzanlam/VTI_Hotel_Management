package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.Guest;
import lombok.Data;

import static hotel.hotel_management.methodSupport.Method.convertStringToEnum;
import static hotel.hotel_management.methodSupport.Method.convertToLocalDate;

@Data
public class GuestRequest {
    private String fullName;
    private String phone;
    private String gender;
    private String birthDate;
    private String idCardNumber;
    private String nation;
    private String address;
    private String image;
    private String note;

    public Guest addGuest() throws Exception {
        Guest guest = new Guest();
        populate(guest);
        guest.setType(Guest.TypeGuest.NEW_GUEST);
        guest.setCountAmount(0);
        guest.setCountStay(0);
        return guest;
    }

    public void updateGuest(Guest guest) throws Exception {
        populate(guest);
    }

    private void populate(Guest guest) throws Exception {
        guest.setFullName(fullName);
        guest.setPhone(phone);
        guest.setGender(convertStringToEnum(Guest.Gender.class, gender));
        guest.setBirthDate(convertToLocalDate(birthDate));
        guest.setIdCardNumber(idCardNumber);
        guest.setNation(nation);
        guest.setAddress(address);
        guest.setImage(image);
        guest.setNote(note);
    }
}

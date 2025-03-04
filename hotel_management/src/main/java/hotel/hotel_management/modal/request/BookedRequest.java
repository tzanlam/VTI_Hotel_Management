package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.Booked;
import lombok.Data;


import static hotel.hotel_management.methodSupport.Method.buildLocalDateTime;
import static hotel.hotel_management.methodSupport.Method.convertStringToEnum;

@Data
public class BookedRequest {
    private String guestName;
    private int roomId;
    private int accountId;
    private String typeStay;
    private String typeCheckin;
    private String checkInTime;
    private String checkInDate;
    private String checkOutTime;
    private String checkOutDate;
    private boolean isBooked = false;

    public Booked addBooked() {
        Booked booked = new Booked();
        booked.setTypeCheckin(convertStringToEnum(Booked.TypeCheckin.class, typeCheckin));
        booked.setTypeStay(convertStringToEnum(Booked.TypeStay.class, typeStay));
        booked.setCheckIn(buildLocalDateTime(checkInDate, checkInTime));
        booked.setCheckOut(buildLocalDateTime(checkOutDate, checkOutTime));
        if (isBooked){
            booked.setStatus(Booked.StatusBooked.BOOKED);
        }else {
            booked.setStatus(Booked.StatusBooked.STAY);
        }
        return booked;
    }

    public void updateBooked(Booked booked) {
        booked.setTypeCheckin(convertStringToEnum(Booked.TypeCheckin.class, typeCheckin));
        booked.setCheckIn(buildLocalDateTime(checkInDate, checkInTime));
        booked.setCheckOut(buildLocalDateTime(checkOutDate, checkOutTime));
    }
}

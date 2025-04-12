package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.constant.StatusOL;
import hotel.hotel_management.modal.entity.Hotel;
import lombok.Data;

@Data
public class HotelRequest {
    private String name;
    private String image;
    private String address;
    private int accountId;

    public Hotel addHotel(){
        Hotel hotel = new Hotel();
        populate(hotel);
        return hotel;
    }

    public void updateHotel(Hotel hotel){
        populate(hotel);
    }

    private void populate(Hotel hotel){
        hotel.setName(name);
        hotel.setImage(image);
        hotel.setAddress(address);
        hotel.setStatus(StatusOL.OPEN);
    }
}

package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.hotel.Hotel;
import lombok.Data;

@Data
public class HotelRequest {
    private String name;
    private String image;
    private String address;

    public Hotel createNewHotel() {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setImage(image);
        hotel.setAddress(address);
        hotel.setStatus(Hotel.HotelStatus.OPEN);
        return hotel;
    }

    public void modifyHotel(Hotel hotel) {
        hotel.setName(name);
        hotel.setImage(image);
        hotel.setAddress(address);
    }
}

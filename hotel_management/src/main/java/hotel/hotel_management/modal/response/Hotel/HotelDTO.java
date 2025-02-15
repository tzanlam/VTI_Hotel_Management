package hotel.hotel_management.modal.response.Hotel;

import hotel.hotel_management.modal.entity.hotel.Hotel;
import lombok.Data;

@Data
public class HotelDTO {
    private int hotelId;
    private String hotelName;
    private String hotelImage;
    private String address;
    private String createDate;
    private String createdTime;
    private String updateDate;
    private String updatedTime;

    public HotelDTO(Hotel hotel) {
        this.hotelId = hotel.getId();
        this.hotelName = hotel.getName();
        this.hotelImage = hotel.getImage();
        this.address = hotel.getAddress();
        this.createDate = String.valueOf(hotel.getCreatedDate().toLocalDate());
        this.createdTime = String.valueOf(hotel.getCreatedDate().toLocalTime());
        this.updateDate = String.valueOf(hotel.getModifiedDate().toLocalDate());
        this.updatedTime = String.valueOf(hotel.getModifiedDate().toLocalTime());
    }
}

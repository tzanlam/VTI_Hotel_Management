package hotel.hotel_management.modal.response.Hotel;

import hotel.hotel_management.modal.entity.hotel.Bar;
import lombok.Data;

@Data
public class BarDTO {
    private int id;
    private String name;
    private int price;
    private int hotelId;
    public BarDTO(Bar bar){
        this.id = bar.getId();
        this.name = bar.getName();
        this.price = (int) bar.getPrice();
        this.hotelId = bar.getHotel().getId();
    }
}

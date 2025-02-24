package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.hotel.Bar;
import lombok.Data;

@Data
public class BarRequest {
    private String name;
    private int price;
    private int hotelId;

    public Bar setBar(){
        Bar bar = new Bar();
        bar.setName(name);
        bar.setPrice(price);
        return bar;
    }
}

package hotel.hotel_management.modal.response.Hotel;

import hotel.hotel_management.modal.entity.hotel.Account;
import hotel.hotel_management.modal.entity.hotel.Hotel;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class HotelDTO {
    private int hotelId;
    private String hotelName;
    private String hotelImage;
    private String address;
    private String createdDate;
    private String createdTime;
    private String updatedDate;
    private String updatedTime;
    private String status;
    private List<Integer> accountId;

    public HotelDTO(Hotel hotel) {
        this.hotelId = hotel.getId();
        this.hotelName = hotel.getName();
        this.hotelImage = hotel.getImage();
        this.address = hotel.getAddress();

        if (Objects.nonNull(hotel.getCreatedDate())) {
            this.createdDate = hotel.getCreatedDate().toLocalDate().toString();
            this.createdTime = hotel.getCreatedDate().toLocalTime().toString();
        } else {
            this.createdDate = "";
            this.createdTime = "";
        }

        if (Objects.nonNull(hotel.getModifiedDate())) {
            this.updatedDate = hotel.getModifiedDate().toLocalDate().toString();
            this.updatedTime = hotel.getModifiedDate().toLocalTime().toString();
        } else {
            this.updatedDate = "";
            this.updatedTime = "";
        }

        this.accountId = Objects.nonNull(hotel.getAccounts())
                ? hotel.getAccounts().stream().map(Account::getId).collect(Collectors.toList())
                : List.of();

        this.status = String.valueOf(hotel.getStatus());
    }
}

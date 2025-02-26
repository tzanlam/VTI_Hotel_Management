package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Account;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountDTO {
    private int id;
    private String email;
    private String image;
    private String fullName;
    private String evaluate;
    private String position;
    private List<HotelDTO> hotels;
    private String status;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.image = account.getImage();
        this.fullName = account.getFullName();
        this.evaluate = account.getEvaluate();
        this.position = String.valueOf(account.getPosition());
        this.hotels = account.getHotels() != null ?
                account.getHotels().stream()
                        .map(HotelDTO :: new)
                        .collect(Collectors.toList()) : new ArrayList<>();
        this.status = String.valueOf(account.getStatus());
    }
}

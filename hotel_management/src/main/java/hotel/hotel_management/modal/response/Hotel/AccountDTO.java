package hotel.hotel_management.modal.response.Hotel;

import hotel.hotel_management.modal.entity.hotel.Account;
import hotel.hotel_management.modal.entity.hotel.Hotel;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class AccountDTO {
    private int accountId;
    private String email;
    private String fullName;
    private String position;
    private String status;
    private String createdDate;
    private String createdTime;
    private String updatedDate;
    private String updatedTime;
    private List<Integer> hotelIds;

    public AccountDTO(Account account) {
        this.accountId = account.getId();
        this.email = account.getEmail();
        this.fullName = account.getFullName();
        this.position = String.valueOf(account.getPosition());
        this.status = String.valueOf(account.getStatus());

        if (account.getCreatedDate() != null) {
            this.createdDate = account.getCreatedDate().toLocalDate().toString();
            this.createdTime = account.getCreatedDate().toLocalTime().toString();
        } else {
            this.createdDate = "";
            this.createdTime = "";
        }

        if (account.getModifiedDate() != null) {
            this.updatedDate = account.getModifiedDate().toLocalDate().toString();
            this.updatedTime = account.getModifiedDate().toLocalTime().toString();
        } else {
            this.updatedDate = "";
            this.updatedTime = "";
        }

        this.hotelIds = Objects.nonNull(account.getHotels())
                ? account.getHotels().stream().map(Hotel::getId).collect(Collectors.toList())
                : List.of();
    }
}

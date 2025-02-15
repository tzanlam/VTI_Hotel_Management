package hotel.hotel_management.modal.response.Hotel;

import hotel.hotel_management.modal.entity.hotel.Account;
import lombok.Data;

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
    public AccountDTO(Account account) {
        this.accountId = account.getId();
        this.email = account.getEmail();
        this.fullName = account.getFullName();
        this.position = String.valueOf(account.getPosition());
        this.status = String.valueOf(account.getStatus());
        this.createdDate = String.valueOf(account.getCreatedDate().toLocalDate());
        this.createdTime = String.valueOf(account.getCreatedDate().toLocalTime());
        this.updatedDate = String. valueOf(account.getModifiedDate().toLocalDate());
        this.updatedTime = String.valueOf(account.getModifiedDate().toLocalTime());
    }
}

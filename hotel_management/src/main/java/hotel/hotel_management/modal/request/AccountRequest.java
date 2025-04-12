package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.constant.StatusAction;
import hotel.hotel_management.modal.entity.Account;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class AccountRequest {
    private String email;
    @NotNull
    private String password;
    private String image;
    private String fullName;
    private String evaluate;


    public Account register(){
        Account account = new Account();
        populate(account);
        account.setPosition(Account.Position.HOTELIER);
        account.setStatus(StatusAction.INACTIVE);
        return account;
    }

    public void putAccount(Account account){
        populate(account);
    }

    public Account addAccountManager(){
        Account account = new Account();
        populate(account);
        account.setPosition(Account.Position.MANAGER);
        account.setStatus(StatusAction.INACTIVE);
        account.setEvaluate(evaluate);
        return account;
    }

    public Account addAccountReception(){
        Account account = new Account();
        populate(account);
        account.setPosition(Account.Position.RECEPTION);
        account.setStatus(StatusAction.INACTIVE);
        account.setEvaluate(evaluate);
        return account;
    }

    private void populate(Account account){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setEmail(email);
        account.setPassword(encoder.encode(password));
        if (image != null) {
            account.setImage(image);
        }else {
            account.setImage(null);
        }
        account.setFullName(fullName);
    }
}

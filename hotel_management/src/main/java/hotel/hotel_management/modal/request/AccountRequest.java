package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.hotel.Account;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static hotel.hotel_management.methodSupport.Method.randomConfirmationCode;

@Data
public class AccountRequest {
    private String email;
    private String password;
    private String fullName;

    public Account hotelier(){
        Account admin = new Account();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setEmail(email);
        admin.setPassword(encoder.encode(password));
        admin.setFullName(fullName);
        admin.setPosition(Account.Position.HOTELIER);
        admin.setConfirmationCode(randomConfirmationCode());
        admin.setStatus(Account.AccountStatus.PENDING);
        return admin;
    }

    public Account receptionist(){
        Account receptionist = new Account();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        receptionist.setEmail(email);
        receptionist.setPassword(encoder.encode(password));
        receptionist.setFullName(fullName);
        receptionist.setPosition(Account.Position.RECEPTIONIST);
        receptionist.setConfirmationCode(randomConfirmationCode());
        receptionist.setStatus(Account.AccountStatus.ACTIVE);
        return receptionist;
    }

    public void modifiedAccount(Account account){
            account.setEmail(email);
            account.setFullName(fullName);
    }
}

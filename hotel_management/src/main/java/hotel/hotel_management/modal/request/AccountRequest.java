package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.constant.StatusAction;
import hotel.hotel_management.modal.entity.Account;
import lombok.Data;

@Data
public class AccountRequest {
    private String email;
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
        return account;
    }

    public Account addAccountReception(){
        Account account = new Account();
        populate(account);
        account.setPosition(Account.Position.RECEPTION);
        account.setStatus(StatusAction.INACTIVE);
        return account;
    }

    private void populate(Account account){
        account.setEmail(email);
        account.setPassword(password);
        account.setImage(image);
        account.setFullName(fullName);
        account.setEvaluate(evaluate);
    }
}

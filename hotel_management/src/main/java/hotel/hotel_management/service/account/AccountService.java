package hotel.hotel_management.service.account;

import hotel.hotel_management.modal.request.AccountRequest;
import hotel.hotel_management.modal.response.AccountDTO;
import hotel.hotel_management.modal.response.AuthResponse;
import hotel.hotel_management.modal.response.HotelDTO;

import java.util.List;

public interface AccountService {
    // get
    List<AccountDTO> getAllAccounts();
    List<HotelDTO> getByAccountId(int accountId);
    AccountDTO getAccountById(int accountId);

    // post
    AccountDTO register(AccountRequest request);
    AccountDTO reception(AccountRequest request);
    AccountDTO manager(AccountRequest request);
    AccountDTO addEvaluate(int accountId, String evaluate);
    AuthResponse login(String email, String password);

    // put
    AccountDTO updateAccount(AccountRequest request, int accountId);
    AccountDTO forgotPassword(String email);
    // deleted
    AccountDTO deleteAccount(int accountId);

    // method confirm
    boolean confirm(String email, String code);
}

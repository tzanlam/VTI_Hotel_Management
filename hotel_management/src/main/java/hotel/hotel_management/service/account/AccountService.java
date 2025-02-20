package hotel.hotel_management.service.account;

import hotel.hotel_management.modal.request.AccountRequest;
import hotel.hotel_management.modal.response.Hotel.AccountDTO;
import hotel.hotel_management.modal.response.AuthResponse;
import jakarta.mail.MessagingException;

import java.util.List;

public interface AccountService {
    // find
    List<AccountDTO> findByHotel(int hotelId);
    AccountDTO findById(int id);

    // update
    AccountDTO updateAccount(AccountRequest request, int accountId);

    // create
    AccountDTO createHotelier(AccountRequest request) throws MessagingException;
    AccountDTO createReceptionist(int hotelId,int hotelierId, AccountRequest request) throws Exception;

    // updateStatus
    AccountDTO updateStatusAccount(int accountId);

    // login
    AuthResponse login(String email, String password) throws MessagingException;
}

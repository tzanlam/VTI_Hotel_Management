package hotel.hotel_management.service.account;

import hotel.hotel_management.config.mailSender.MailSender;
import hotel.hotel_management.config.security.JwtToken;
import hotel.hotel_management.modal.entity.hotel.Account;
import hotel.hotel_management.modal.request.AccountRequest;
import hotel.hotel_management.modal.response.Hotel.AccountDTO;
import hotel.hotel_management.modal.response.AuthResponse;
import hotel.hotel_management.repository.AccountRepository;
import jakarta.mail.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IAccountService implements AccountService{
    private final AccountRepository accountRepository;
    private final MailSender mailSender;
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final PasswordEncoder passwordEncoder;

    public IAccountService(AccountRepository accountRepository, MailSender mailSender, AuthenticationManager authenticationManager, JwtToken jwtToken, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.mailSender = mailSender;
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<AccountDTO> findByHotel(int hotelId) {
        List<Account> account = accountRepository.findByHotelId(hotelId);
        return account.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO findById(int id) {
        Account account = accountRepository.findById(id).orElse(null);
        assert account != null;
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO updateAccount(AccountRequest request, int accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (Objects.nonNull(account)) {
            request.modifiedAccount(account);
            accountRepository.save(account);
            return new AccountDTO(account);
        }
        return null;
    }

    @Override
    public AccountDTO createHotelier(AccountRequest request) throws MessagingException {
        Account account = request.hotelier();
        accountRepository.save(account);
        mailSender.sendMailFromAdmin(account.getEmail(), "Mã xác nhận đăng kí tài khoản", account.getConfirmationCode());
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO createReceptionist(AccountRequest request) {
        Account account = request.receptionist();
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO updateStatusAccount(int accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (Objects.nonNull(account)) {
            if (account.getStatus().equals(Account.AccountStatus.INACTIVE)){
                account.setStatus(Account.AccountStatus.ACTIVE);
            }else {
                account.setStatus(Account.AccountStatus.INACTIVE);
            }
            return new AccountDTO(account);
        }
        return null;
    }

    @Override
    public AuthResponse login(String email, String password) throws MessagingException {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        Account account = accountRepository.findByEmail(userDetails.getUsername());
        if (Objects.isNull(account)) {
            throw new RuntimeException("Account not found with email: " + email);
        }
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new RuntimeException("Password not match");
        }
        String token = jwtToken.generateToken(userDetails);
        return new AuthResponse(token, email, String.valueOf(account.getPosition()), userDetails.getAuthorities());
    }
}

package hotel.hotel_management.service.account;

import hotel.hotel_management.config.mailSender.JavaMailSender;
import hotel.hotel_management.config.security.JwtToken;
import hotel.hotel_management.modal.constant.StatusAction;
import hotel.hotel_management.modal.entity.Account;
import hotel.hotel_management.modal.request.AccountRequest;
import hotel.hotel_management.modal.response.AccountDTO;
import hotel.hotel_management.modal.response.AuthResponse;
import hotel.hotel_management.modal.response.HotelDTO;
import hotel.hotel_management.repository.AccountRepository;
import hotel.hotel_management.repository.HotelRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static hotel.hotel_management.methodSupport.Method.randomConfirmationCode;

@Service
public class IAccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final HotelRepository hotelRepository;
    private final JavaMailSender mailSender;
    private final JwtToken jwtToken;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public IAccountServiceImpl(AccountRepository accountRepository, HotelRepository hotelRepository, JavaMailSender mailSender, JwtToken jwtToken, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.hotelRepository = hotelRepository;
        this.mailSender = mailSender;
        this.jwtToken = jwtToken;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getByAccountId(int accountId) {
        return hotelRepository.findByAccountId(accountId).stream().map(HotelDTO::new).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccountById(int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO register(AccountRequest request) {
        Account account = request.register();
        account.setVertical(randomConfirmationCode());
        accountRepository.save(account);
        mailSender.sendMailFromAdmin(request.getEmail(), "Register account with auth Hotelier", account.getVertical(), true);
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO reception(AccountRequest request) {
        Account account = request.addAccountReception();
        account.setVertical(randomConfirmationCode());
        accountRepository.save(account);
        mailSender.sendMailFromAdmin(request.getEmail(), "Register account Reception", account.getVertical(), true);
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO manager(AccountRequest request) {
        Account account = request.addAccountManager();
        account.setVertical(randomConfirmationCode());
        accountRepository.save(account);
        mailSender.sendMailFromAdmin(request.getEmail(), "Register account Manager", account.getVertical(), true);
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO addEvaluate(int accountId, String evaluate) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        account.setEvaluate(evaluate);
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    @Override
    public AuthResponse login(String email, String password) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtToken.generateToken(userDetails);
        return new AuthResponse(token, email, account.getPosition().toString(), userDetails.getAuthorities());
    }

    @Override
    public AccountDTO updateAccount(AccountRequest request, int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        request.putAccount(account);
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO forgotPassword(String email, String newPassword) {
        Account account = accountRepository.findByEmail(email);
        if (Objects.isNull(account)) {
            throw new RuntimeException("Account not found");
        }
        account.setPassword(passwordEncoder.encode(newPassword));
        account.setVertical(randomConfirmationCode());
        mailSender.sendMailFromAdmin(account.getEmail(), "Forgot password", account.getVertical(), true);
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO deleteAccount(int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        account.setStatus(
                account.getStatus() == StatusAction.ACTIVE ? StatusAction.INACTIVE : StatusAction.ACTIVE
        );
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    @Override
    public boolean confirm(int accountId, String code) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account not found")
        );
        if (code.equals(account.getVertical())) {
            account.setStatus(StatusAction.ACTIVE);
            account.setVertical(null);
            accountRepository.save(account);
            return true;
        }
        return false;
    }
}

package hotel.hotel_management.config;

import hotel.hotel_management.modal.entity.Account;
import hotel.hotel_management.repository.AccountRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);
        if (Objects.nonNull(account)) {
            return new User(
                    email,
                    account.getPassword(),
                    AuthorityUtils.createAuthorityList(account.getPosition().name())
            );
        }
        throw new UsernameNotFoundException(email);
    }
}

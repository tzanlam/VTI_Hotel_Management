package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Account;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class AuthResponse {
    private int accountId;
    private String fullName;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthResponse(Account account, String token, Collection<? extends GrantedAuthority> authorities) {
        this.accountId = account.getId();
        this.fullName = account.getFullName();
        this.token = token;
        this.authorities = authorities;
    }
}

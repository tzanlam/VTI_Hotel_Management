package hotel.hotel_management.modal.response;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class AuthResponse {
    private String token;
    private String email;
    private String position;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthResponse(String token, String email, String position, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.email = email;
        this.position = position;
        this.authorities = authorities;
    }
}

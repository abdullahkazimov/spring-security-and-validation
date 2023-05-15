package abdkzmv.wm2.assignment2.model.dto;

import abdkzmv.wm2.assignment2.model.entity.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    private String username;
    private String password;
    private String email;

    public User toUser(PasswordEncoder encoder) {
        return new User(username, encoder.encode(password), email);
    }
}

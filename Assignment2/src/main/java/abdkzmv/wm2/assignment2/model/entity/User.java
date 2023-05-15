package abdkzmv.wm2.assignment2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100, message = "username length must be between {min} and {max}")
    private String username;

    @NotBlank
    @Size(min = 1, max = 100, message = "username length must be between {min} and {max}")
    private String password;

    @NotBlank
    @Size(min = 1, max = 100, message = "username length must be between {min} and {max}")
    private String email;

    @NotNull
    @Size(min = 1, max = 100, message = "username length must be between {min} and {max}")
    private String roles;

    @Transient
    private List<Role> rolesList = new ArrayList<>(Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_ADMIN")));
    @Transient
    private List<String> authorities =  new ArrayList<>(Arrays.asList("ROLE_USER"));

    public List<GrantedAuthority> getAuthorities() {
        return authorities.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User addRole(String authority) {
        this.authorities.add(authority);
        return this;
    }

    @PrePersist
    @PreUpdate
    private void saveRoles() {this.roles = String.join(";", this.authorities);}

    @PostLoad
    private void readRoles() {this.authorities = Arrays.stream(this.roles.split(";")).collect(Collectors.toList());}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
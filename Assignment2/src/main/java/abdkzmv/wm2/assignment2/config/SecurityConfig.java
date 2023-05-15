package abdkzmv.wm2.assignment2.config;

import abdkzmv.wm2.assignment2.model.entity.User;
import abdkzmv.wm2.assignment2.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;


@Configuration
public class SecurityConfig {

    public static String username;

    @Bean
    public static String currentUser() {
        return username;
    }

    @Bean
    public static void nullUser() {
        username = null;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return username -> {
            this.username = username;

            Optional<User> res = repo.findByUsername(username);
            return res.orElseThrow(() ->
                    new UsernameNotFoundException(username + "not found")
            );
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user/*").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin/**", "/user/movies/**").hasRole("ADMIN")
                .requestMatchers("/", "/signup/**").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();

        return http.build();
    }
}

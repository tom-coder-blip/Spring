package tacos.tacos05.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tacos.tacos05.User;
import tacos.tacos05.data.UserRepository;

@Configuration // Marks this as a configuration class
public class SecurityConfig {

    // Password encoder used for hashing passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Tells Spring Security how to load users from the database
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                return user; // User implements UserDetails
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    // Main security configuration
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // Which pages require login
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/design", "/orders").hasRole("USER")
                        .anyRequest().permitAll() // Everything else is public
                )
                // Custom login page
                .formLogin(form -> form
                        .loginPage("/login")
                )
                // After logout -> redirect to home page
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                // Allow H2 console to work (disable CSRF for it)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                // Allow H2 console frames
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                .build();
    }

}

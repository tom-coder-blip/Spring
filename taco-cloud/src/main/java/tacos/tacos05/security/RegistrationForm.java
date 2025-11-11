package tacos.tacos05.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import tacos.tacos05.User;

@Data // Lombok: generates getters/setters automatically
public class RegistrationForm {

    // Fields submitted from the registration form
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    // Converts this form into a User entity
    // Encodes the password before saving
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username,
                passwordEncoder.encode(password),
                fullname,
                street,
                city,
                state,
                zip,
                phone
        );
    }
}
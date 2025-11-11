package tacos.tacos05.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.tacos05.data.UserRepository;

@Controller // Marks this as a Spring MVC controller
@RequestMapping("/register") // Base URL for registration
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    // Constructor injection for dependencies
    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Show the registration page
    @GetMapping
    public String registerForm() {
        return "registration"; // returns registration.html template
    }

    // Handle form submission
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        // Convert form → User, encode password, save to DB
        userRepo.save(form.toUser(passwordEncoder));

        return "redirect:/login"; // Redirect to login page
    }

}

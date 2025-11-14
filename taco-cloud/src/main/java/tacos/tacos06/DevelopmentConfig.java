package tacos.tacos06;
// package name (folder) where this file belongs

// --- Spring Boot and Spring Framework imports ---
import org.springframework.boot.CommandLineRunner;// lets you run code automatically when the app starts
import org.springframework.context.annotation.Bean;// marks a method that creates a Spring bean
import org.springframework.context.annotation.Configuration;// marks this class as a configuration class
import org.springframework.context.annotation.Profile; // controls which environments this config runs in
import org.springframework.security.crypto.password.PasswordEncoder; // used to securely encode passwords

// --- App-specific imports ---
import tacos.tacos06.Ingredient.Type;                    // imports the Ingredient Type enum
import tacos.tacos06.data.IngredientRepository;          // used to access and save Ingredient data
import tacos.tacos06.data.UserRepository;                // used to access and save User data

@Profile("!prod")                                   // only run when NOT in production
@Configuration                                    // marks this as a Spring configuration class
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder) { // runs code at startup; injects repos + encoder
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {  // method that runs automatically at app startup

                repo.deleteAll();                 // clear all existing ingredients
                userRepo.deleteAll();             // clear all existing users

                // --- add fresh ingredients to the database ---
                repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
                repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
                repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
                repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
                repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
                repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
                repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
                repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
                repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
                repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

                // --- add a sample user for testing ---
                userRepo.save(new User("habuma", encoder.encode("password"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234"));
            }
        };
    }

}
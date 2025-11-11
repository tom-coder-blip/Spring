package tacos.tacos04;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tacos.tacos04.Ingredient.Type;
import tacos.tacos04.data.IngredientRepository;

@SpringBootApplication   // Enables Spring Boot auto-configuration and component scanning
public class TacoCloudApplication {

    public static void main(String[] args) {
        // Starts the entire Spring Boot application (Tomcat + Beans + Config)
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    // Runs right after the app starts. Used to pre-load data into MongoDB.
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {

        // CommandLineRunner runs after Spring Boot has finished initializing.
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                // Insert default ingredients into MongoDB.
                // This ensures the app has data when it starts.
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
            }
        };
    }
}
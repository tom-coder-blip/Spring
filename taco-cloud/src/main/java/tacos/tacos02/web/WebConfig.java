package tacos.tacos02.web;

// Spring configuration classes
import org.springframework.context.annotation.Configuration;  // Marks this as a configuration class
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry; // Registers simple controllers for views
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;        // Allows customizing Spring MVC settings

@Configuration  // Tells Spring this class provides configuration
public class WebConfig implements WebMvcConfigurer {

    // Adds simple view controllers (no Java logic needed)
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // When user visits "/", show the "home" view (home.html)
        registry.addViewController("/").setViewName("home");
    }

}

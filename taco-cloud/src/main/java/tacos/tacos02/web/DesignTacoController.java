package tacos.tacos02.web;

//This controller manages the “design a taco” UI flow

import java.util.Arrays;                 // Arrays utility for building lists
import java.util.List;                   // List interface for collections
import java.util.stream.Collectors;      // For collecting stream results
import org.springframework.stereotype.Controller; // Marks class as a Spring MVC controller
import org.springframework.ui.Model;     // Model holds data passed to the view
import org.springframework.web.bind.annotation.GetMapping; // Handles GET requests
import org.springframework.web.bind.annotation.ModelAttribute; // Binds model attributes
import org.springframework.web.bind.annotation.PostMapping; // Handles POST requests
import org.springframework.web.bind.annotation.RequestMapping; // Base path mapping for controller
import org.springframework.web.bind.annotation.SessionAttributes; // Store model attribute in HTTP session

import lombok.extern.slf4j.Slf4j;        // Lombok: creates a 'log' field for logging
import tacos.tacos02.Taco;              // Taco model (form-backed object)
import tacos.tacos02.TacoOrder;         // TacoOrder model (session-scoped order)
import tacos.tacos02.Ingredient.Type;   // Ingredient.Type enum
import tacos.tacos02.Ingredient;        // Ingredient model

import jakarta.validation.Valid;        // Triggers bean validation on a method parameter
import org.springframework.validation.Errors; // Holds validation errors after binding

@Slf4j
@Controller
@RequestMapping("/design")           // All handler methods handle paths under /design
@SessionAttributes("tacoOrder")     // Keep "tacoOrder" in the HTTP session across requests
public class DesignTacoController {

    // Adds grouped ingredient lists to the model before any request handler runs
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        // For each ingredient type, add a model attribute named after the type (e.g., "wrap")
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    // Ensure a TacoOrder is available in the model (and stored in session)
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    // Ensure an empty Taco exists in the model for the form to bind to
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    // Handle GET /design — show the taco design form
    @GetMapping
    public String showDesignForm() {
        return "design"; // view name (e.g., resolves to design.html)
    }

    // Handle POST /design — process submitted taco form with validation
    @PostMapping
    public String processTaco(
            @Valid Taco taco,       // Validate this Taco object
            Errors errors,          // Holds validation errors (if any)
            @ModelAttribute TacoOrder tacoOrder) { // Get the TacoOrder from model/session

        // If validation failed, redisplay the form so user can correct errors
        if (errors.hasErrors()) {
            return "design";
        }

        // Validation passed — add taco to the session order and continue
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current"; // Redirect to the current order/checkout page
    }

    // Helper: filter ingredients by their Type and return as a List
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
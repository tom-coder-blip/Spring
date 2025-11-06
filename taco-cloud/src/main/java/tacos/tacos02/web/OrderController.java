package tacos.tacos02.web;

//OrderController.java class is part of your Taco Cloud Spring Boot web app.
// It manages the ordering process — specifically, it displays the order form and handles its submission.

// Spring annotations for handling web requests
import org.springframework.stereotype.Controller;     // Marks this class as a Spring MVC controller
import org.springframework.ui.Model;                // Used to send data to the view
import org.springframework.web.bind.annotation.GetMapping;  // Handles GET requests
import org.springframework.web.bind.annotation.ModelAttribute; // Adds model attributes before requests
import org.springframework.web.bind.annotation.PostMapping; // Handles POST requests (form submissions)

import jakarta.validation.Valid;                    // Enables form validation
import org.springframework.validation.Errors;        // Holds validation errors

import tacos.tacos02.TacoOrder;                      // The model representing a taco order

@Controller  // Marks this as a controller class
public class OrderController {

    // Creates a TacoOrder object and adds it to the model for all requests
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    // Handles GET /orders/current — shows the order form page
    @GetMapping("/orders/current")
    public String orderForm() {
        return "orderForm"; // Returns the name of the view (orderForm.html)
    }

    // Handles POST /orders — processes submitted form data
    @PostMapping("/orders")
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors) {
        // If form has validation errors, redisplay the form
        if (errors.hasErrors()) {
            return "orderForm";
        }

        // Otherwise, print order to console and redirect to homepage
        System.out.println("Order submitted: " + tacoOrder);
        return "redirect:/"; // Redirect after successful submission
    }
}
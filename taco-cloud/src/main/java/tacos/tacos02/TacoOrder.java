package tacos.tacos02; // Organizes this class in the tacos.tacos02 package

//the TacoOrder class represents a customer’s taco order and includes validation for all input fields.

// Imports for validation and data handling
import jakarta.validation.constraints.Digits; // Validates numeric digits
import jakarta.validation.constraints.NotBlank; // Ensures field is not null or empty
import jakarta.validation.constraints.Pattern; // Ensures field matches a pattern
import org.hibernate.validator.constraints.CreditCardNumber; // Validates credit card number format
import java.util.List; // List interface for storing tacos
import java.util.ArrayList; // Used to create a new list
import lombok.Data; // Generates getters, setters, toString, equals, hashCode

@Data // Lombok annotation to auto-generate common methods
public class TacoOrder {

    // Delivery information (all required)
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    // Payment details with validation
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    // List of tacos in this order
    private List<Taco> tacos = new ArrayList<>();

    // Adds a taco to the order
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
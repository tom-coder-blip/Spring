package tacos.tacos04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data                       // Lombok generates getters, setters, toString, etc.
@Document                   // Marks this class as a MongoDB document
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    // Needed because orders may be stored in the HTTP session

    @Id
    private String id;       // Unique ID for MongoDB

    private Date placedAt = new Date();
    // Timestamp when the order is placed

    // -------- Delivery information (all required) --------
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    // -------- Payment information --------
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;    // Credit card number

    @Pattern(
            regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Must be formatted MM/YY"
    )
    private String ccExpiration;   // Expiration formatted as MM/YY

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;          // 3-digit CVV

    // -------- List of designed tacos --------
    private List<Taco> tacos = new ArrayList<>();

    // Helper method to add a taco into the order
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

}
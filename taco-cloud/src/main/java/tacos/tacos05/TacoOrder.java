package tacos.tacos05;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// JPA
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

// Validation for form fields
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data // Generates getters/setters
@Entity // JPA entity
@Table(name="Taco_Order") // Custom table name
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // When the order was placed
    private Date placedAt;

    @ManyToOne // Many orders belong to one user
    private User user;

    // Delivery information – must not be blank
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

    // Credit card validation
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(
            regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY"
    )
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @ManyToMany(targetEntity=Taco.class) // Order contains many tacos
    private List<Taco> tacos = new ArrayList<>();

    // Add taco to order
    public void addTaco(Taco design) {
        this.tacos.add(design);
    }

    // Automatically set timestamp before saving
    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
package tacos.tacos05;

import java.util.Date;
import java.util.List;

// JPA entity annotations
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;

// Validation annotations for form input
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data // Auto-generates getters/setters
@Entity // Marks this class as a JPA entity
public class Taco {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // Auto-increment ID
    private Long id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    // Date the taco was created
    private Date createdAt;

    @ManyToMany(targetEntity=Ingredient.class) // Taco has many ingredients
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    // Runs before saving to DB, sets timestamp
    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
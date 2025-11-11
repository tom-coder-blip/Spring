package tacos.tacos05;

// JPA annotation to mark this class as a database entity
import jakarta.persistence.Entity;
// Marks this field as the primary key of the entity
import jakarta.persistence.Id;

// Lombok: auto-generates getters, setters, toString, etc.
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// Tells JPA that this field should be stored as a string enum
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Data // Lombok: generates boilerplate like getters/setters
@RequiredArgsConstructor // Creates constructor for final fields
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true) // Needed by JPA
@Entity // JPA entity mapped to a database table
public class Ingredient {

    @Id // Primary key
    private final String id;

    // Ingredient name (e.g., "Cheddar")
    private final String name;

    @Enumerated(EnumType.STRING) // Store enum as string not number
    private final Type type;

    // Different categories for ingredients
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
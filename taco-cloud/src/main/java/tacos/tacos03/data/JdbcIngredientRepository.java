package tacos.tacos03.data;

// This class implements IngredientRepository using Spring’s JdbcTemplate.
// It provides simple SQL operations to load and save Ingredient records.
// Row mapping is handled by a helper method for cleaner code.

// JDBC ResultSet classes for reading database rows
import java.sql.ResultSet;
import java.sql.SQLException;
// Used for storing query results
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
// Marks this class as a Spring repository bean
import org.springframework.stereotype.Repository;

import tacos.tacos03.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate;

    // Inject JdbcTemplate to run SQL queries
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        // Query all ingredients and convert each row to an Ingredient object
        return jdbcTemplate.query(
                "select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        // Query ingredient by ID (may return zero results)
        List<Ingredient> results = jdbcTemplate.query(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient,
                id);

        // Return Optional depending on whether we found a row
        return results.size() == 0
                ? Optional.empty()
                : Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        // Insert a new ingredient row into the database
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    // Converts a database row into an Ingredient object
    private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
            throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type")));
    }

  /*
  // Old version using a manual RowMapper implementation
  @Override
  public Ingredient findById(String id) {
    return jdbcTemplate.queryForObject(
        "select id, name, type from Ingredient where id=?",
        new RowMapper<Ingredient>() {
          public Ingredient mapRow(ResultSet rs, int rowNum)
              throws SQLException {
            return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
          };
        }, id);
  }
   */
}

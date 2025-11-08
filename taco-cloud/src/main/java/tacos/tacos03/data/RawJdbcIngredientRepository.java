package tacos.tacos03.data;

// This class implements IngredientRepository using raw JDBC calls.
// It manually opens connections, prepares statements, and maps results by hand.
// It exists mainly to show how much cleaner JdbcTemplate makes JDBC code.

// JDBC classes for manual connection handling
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Used for storing query results
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import tacos.tacos03.Ingredient;

/**
 * Raw JDBC implementation used to compare with JdbcIngredientRepository.
 * Shows how much boilerplate is required without JdbcTemplate.
 */
public class RawJdbcIngredientRepository implements IngredientRepository {

    private DataSource dataSource;

    // Inject the DataSource to manually open database connections
    public RawJdbcIngredientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Open a connection manually
            connection = dataSource.getConnection();

            // Prepare the SQL query
            statement = connection.prepareStatement(
                    "select id, name, type from Ingredient");

            // Run the query
            resultSet = statement.executeQuery();

            // Map each row to an Ingredient object
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        Ingredient.Type.valueOf(resultSet.getString("type")));
                ingredients.add(ingredient);
            }

        } catch (SQLException e) {
            // In real apps you'd log this or rethrow a custom exception
        } finally {
            // Close result set, statement, and connection manually
            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) {}
            }
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) {}
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) {}
            }
        }

        return ingredients;
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Open connection manually
            connection = dataSource.getConnection();

            // Prepare the query with a placeholder
            statement = connection.prepareStatement(
                    "select id, name, type from Ingredient where id=?");
            statement.setString(1, id);

            resultSet = statement.executeQuery();

            Ingredient ingredient = null;

            // If a row exists, map it to an Ingredient
            if (resultSet.next()) {
                ingredient = new Ingredient(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        Ingredient.Type.valueOf(resultSet.getString("type")));
            }

            return Optional.ofNullable(ingredient);

        } catch (SQLException e) {
            // Normally you'd log or rethrow this
        } finally {
            // Manual cleanup of JDBC resources
            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) {}
            }
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) {}
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) {}
            }
        }

        return Optional.empty();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        // Not implemented here: this class is only used for comparison
        return null;
    }

}

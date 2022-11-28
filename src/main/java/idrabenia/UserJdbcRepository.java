package idrabenia;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserJdbcRepository {

    @Inject
    DataSource dataSource;

    public void create(String name) {
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement("INSERT INTO USERS (name) VALUES (?)")) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            throw new JdbcAppException(e);
        }
    }

    public List<User> findAll() {
        var result = new ArrayList<User>();

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM USERS");
             var rs = statement.executeQuery()) {

            while (rs.next()) {
                var user = new User(rs.getLong(1), rs.getString(2));
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new JdbcAppException(e);
        }
    }

    public static class JdbcAppException extends RuntimeException {
        public JdbcAppException(Throwable cause) {
            super(cause);
        }
    }

}

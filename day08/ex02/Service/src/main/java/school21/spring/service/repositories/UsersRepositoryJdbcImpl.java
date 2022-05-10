package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("jdbc")
public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("hikariDataSource")DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        String command = "SELECT * FROM users WHERE id = " + id;
        try (Connection cn = dataSource.getConnection();
             Statement st = cn.createStatement())
        {
            ResultSet resultSet = st.executeQuery(command);
            if (resultSet.next())
                return new User(resultSet.getInt(1), resultSet.getString(2));
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {

        List<User> list = new ArrayList<>();
        try (Connection cn = dataSource.getConnection();
             Statement st = cn.createStatement())
        {
            ResultSet resultSet = st.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                list.add(new User(resultSet.getInt(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(User entity) {
        try (Connection cn = dataSource.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("INSERT INTO" +
                     " users VALUES(?, ?);"))
        {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try (Connection cn = dataSource.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("UPDATE" +
                     " users SET email = ? WHERE id = ?"))
        {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection cn = dataSource.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("DELETE FROM" +
                     " users WHERE id = ?"))
        {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String command = "SELECT * FROM users WHERE email = '" + email + "'";
        try (Connection cn = dataSource.getConnection();
             Statement st = cn.createStatement())
        {
            ResultSet resultSet = st.executeQuery(command);
            if (resultSet.next())
                return Optional.of(new User(resultSet.getInt(1), resultSet.getString(2)));
            else
                return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

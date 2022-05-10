package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("jdbcTemplate")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("driverManagerDataSource")DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = " + id, new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO users VALUES(?, ?)", entity.getId(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users SET email = ? WHERE id = ?", entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> userList = jdbcTemplate.
                query("SELECT * FROM users WHERE email = '" + email + "'", new BeanPropertyRowMapper<>(User.class));
        if (userList.isEmpty())
            return Optional.empty();
        return Optional.of(userList.get(0));
    }
}

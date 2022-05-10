package school21.spring.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

@Component("userService")
public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("jdbcTemplate") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        int pass = 0;
        for (int i = 0; i < 8; i++) {
            pass += pass * 10 + ((int)(Math.random() * 9) + 1);
        }
        usersRepository.save(new User(pass, email));
        return String.valueOf(pass);
    }
}

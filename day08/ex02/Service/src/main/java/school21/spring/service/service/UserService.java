package school21.spring.service.service;

import org.springframework.stereotype.Component;

@Component
public interface UserService {
    String signUp(String email);
}

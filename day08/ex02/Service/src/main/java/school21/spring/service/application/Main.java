package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.service.UserService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersRepository usersRepository = context.getBean("jdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll () );
        System.out.println(usersRepository.findById(1l));
        System.out.println(usersRepository.findByEmail("kek1"));
        usersRepository = context.getBean("jdbc", UsersRepository.class);
        System.out.println(usersRepository.findAll () );
        System.out.println(usersRepository.findById(1l));
        System.out.println(usersRepository.findByEmail("kek1"));
        UserService service = context.getBean("userService", UserService.class);
        service.signUp("EMAIL");
        System.out.println(usersRepository.findAll () );
    }
}

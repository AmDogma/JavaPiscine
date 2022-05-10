package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.util.Optional;

public class Program {
    public static void main(String[] args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> messageOptional = messagesRepository.findById(2L);
        if (messageOptional.isPresent()) {

            Message message = messageOptional.get();

            System.out.println(message);
            message.setText("NEW");
            message.setDateTime(null);
            messagesRepository.update(message);
            System.out.println(message);
            messagesRepository.save(message);
        }
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            System.out.println(message);
            User user = new User(11l, "1", "1", null, null);
            message.setAuthor(user);
            try {
                messagesRepository.update(message);
            } catch (NotSavedSubEntityException e) {
                e.printStackTrace();
            }
            System.out.println(message);
        }
    }
}

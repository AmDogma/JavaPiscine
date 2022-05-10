package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;
import java.util.Optional;

public interface MessagesRepository {
    public Optional<Message> findById(Long id);
    public void save(Message message) throws NotSavedSubEntityException;
    public void update(Message message);

}

package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    DataSource dataSource;
    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void update(Message message) {
        String command = "UPDATE chat.messages SET message = '"
                + message.getText()
                + "' , dateTime = "
                + message.getDateTime()
                + " , author = "
                + message.getAuthor().getId()
                + " , room = "
                + message.getRoom().getId()
                + " WHERE id = " + message.getId();
        try (Connection cn = dataSource.getConnection();
             Statement st = cn.createStatement())
        {
            st.execute(command);
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        String command = "SELECT * FROM chat.messages WHERE id = " + id;
        try (Connection cn = dataSource.getConnection();
            Statement st = cn.createStatement())
        {
            ResultSet resultSet = st.executeQuery(command);
            User user = new User(1l, "1", "1", null, null);
            Chatroom room = new Chatroom(1l, "1", user, null);
            if (resultSet.next())
                return Optional.of(new Message(resultSet.getLong(1),user,
                        room, resultSet.getString(4), resultSet.getTimestamp(5)));
            else
                return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        String command = "INSERT INTO chat.messages (author, room, message) VALUES (?, ?, ?)";
        if (message.getAuthor() == null || message.getRoom() == null)
            throw new NotSavedSubEntityException();
        try (Connection cn = dataSource.getConnection();
             PreparedStatement pst = cn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS))
        {
            pst.setLong(1, message.getAuthor().getId());
            pst.setLong(2, message.getRoom().getId());
            pst.setString(3, message.getText());
            pst.execute();
            ResultSet resultSet = pst.getGeneratedKeys();
            if (resultSet == null || !resultSet.next())
                throw new NotSavedSubEntityException();
            message.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }
}

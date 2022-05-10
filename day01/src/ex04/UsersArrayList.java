package ex04;

import java.util.Objects;

public class UsersArrayList implements UsersList {
    private static final int DEFAULT_CAPACITY = 10;
    private User[] users = new User[DEFAULT_CAPACITY];
    private int capacity = DEFAULT_CAPACITY;
    private int size = 0;

    private void addCapacity(int capacity) {
        if (capacity > this.capacity) {
            if (calculateCapacity() > capacity)
                capacity = calculateCapacity();
            User[] users = new User[capacity];
            for (int i = 0; i < this.size; i++) {
                users[i] = this.users[i];
            }
            this.users = users;
            this.capacity = capacity;
        }
    }

    private int calculateCapacity() {
        return this.capacity + this.capacity / 2 + this.capacity % 2;
    }

    @Override
    public void add(User user) {
        if (Objects.isNull(user)) {
            System.err.println("UsersArrayList: Error. User can't be null");
            System.exit(-1);
        }
        addCapacity(size + 1);
        this.users[size] = user;
        size++;
    }

    @Override
    public User getById(int id) {
        for (int i = 0; i < this.size; i++) {
            if (users[i].getId() == id)
                return users[i];
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getByIndex(int index) {
        if (index >= size || index < 0)
            throw new UserNotFoundException();
        return users[index];
    }

    @Override
    public int size() {
        return size;
    }
}

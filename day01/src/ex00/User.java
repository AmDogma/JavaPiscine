package ex00;

public class User {
    final private int id;
    final private String name;
    private int balance;

    public User(String name, int id, int balance) {
        this.name = name;
        this.id = id;
        if (balance < 0) {
            balance = 0;
            System.out.println("Balance can't be lower than 0. " + name + "'s balance corrected to 0");
        }
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean outcome(int balance) {
        if (balance >= 0 || getBalance() + balance < 0)
            return false;
        setBalance(getBalance() + balance);
        return true;
    }

    public boolean income(int balance) {
        if (balance < 1)
            return false;
        setBalance(getBalance() + balance);
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

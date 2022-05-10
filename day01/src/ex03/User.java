package ex03;

import java.util.UUID;

public class User {
    final private int id = UserIdsGenerator.getInstance().generateId();
    final private String name;
    private int balance;
    final private TransactionsList  transactions = new TransactionsLinkedList();

    public User(String name, int balance) {
        this.name = name;
        if (balance < 0) {
            balance = 0;
            System.out.println("Balance can't be lower than 0. " + name + "'s balance corrected to 0");
        }
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deleteTransaction(UUID id) {
        transactions.remove(id);
    }

    public Transaction[] transactionsArray() {
        return transactions.toArray();
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

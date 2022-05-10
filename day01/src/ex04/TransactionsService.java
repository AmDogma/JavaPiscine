package ex04;

import java.util.UUID;

public class TransactionsService {

    private final UsersList usersList = new UsersArrayList();

    public void addUser(User user) {
        usersList.add(user);
    }

    public int getBalance(int ID) throws UserNotFoundException {
            return usersList.getById(ID).getBalance();
    }

    public void transaction(int senderID, int recipientID, int amount) throws UserNotFoundException {
        User sender = usersList.getById(senderID);
        User recipient = usersList.getById(recipientID);
        if ((amount < 1) || sender.getBalance() < amount || senderID == recipientID) {
            throw new TransactionNotFoundException();
        }
        UUID uuid = UUID.randomUUID();
        sender.outcome(-amount);
        sender.addTransaction(new Transaction(recipient, sender, Transaction.TransferType.OUTCOME, amount, uuid));
        recipient.income(amount);
        recipient.addTransaction(new Transaction(recipient, sender, Transaction.TransferType.INCOME, amount, uuid));
    }

    public Transaction[] getTransactions(int ID) {
        return usersList.getById(ID).transactionsArray();
    }

    public Transaction[] unpairedTransactions() {
        TransactionsList result = new TransactionsLinkedList();
        for (int i = 0; i < usersList.size(); i++) {
            for (Transaction t : usersList.getByIndex(i).transactionsArray()) {
                if (!findTransaction(t.getId(), t.getTYPE())) {
                    result.add(t);
                }
            }
        }
        return result.toArray();
    }

    private boolean findTransaction(UUID ID, Transaction.TransferType type) {
        for (int i = 0; i < usersList.size(); i++) {
            for (Transaction t : usersList.getByIndex(i).transactionsArray()) {
                if (t.getId().equals(ID) && !type.equals(t.getTYPE())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void removeTransaction(int userID, UUID transactionID) throws RuntimeException {
        usersList.getById(userID).deleteTransaction(transactionID);
    }
}

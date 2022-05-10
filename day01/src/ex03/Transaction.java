package ex03;

import java.util.Objects;
import java.util.UUID;

public class Transaction {
    final private UUID id = UUID.randomUUID();
    final private User recipient;
    final private User sender;
    final private TransferType transferType;
    private int transferAmount;

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public enum TransferType {
        INCOME,
        OUTCOME
    }

    public Transaction(User recipient, User sender, Transaction.TransferType transferType, int transferAmount) {
        if (Objects.isNull(sender) || Objects.isNull(recipient)) {
            System.out.println("Transaction: Error. Sender or recipient can't be NULL");
            System.exit(-1);
        }
        if (sender.getBalance() < transferAmount) {
            System.out.println("Transaction: Not enough balance. Transfer amount will be " + sender.getBalance());
            transferAmount = sender.getBalance();
        }
        if (!sender.outcome(-transferAmount)) {
            transferAmount = 0;
            System.out.println("Transaction: Error in sender transaction. Transaction " + id + " canceled!");
        }
        else if (!recipient.income(transferAmount)) {
            sender.income(transferAmount);
            transferAmount = 0;
            System.out.println("Transaction: Error in recipient income. Transaction " + id + " canceled!");
        }
        else
            System.out.println(id + " successful!");
        this.recipient = recipient;
        this.sender = sender;
        this.transferType = transferType;
        this.transferAmount = transferAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", transferType=" + transferType +
                ", transferAmount=" + transferAmount +
                '}';
    }
}

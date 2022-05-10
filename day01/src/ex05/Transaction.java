package ex05;

import java.util.UUID;

public class Transaction {
    final private UUID ID;
    final private User RECIPIENT;
    final private User SENDER;
    final private TransferType TYPE;
    private final int AMOUNT;

    public UUID getId() {
        return ID;
    }

    public User getRECIPIENT() {
        return RECIPIENT;
    }

    public User getSENDER() {
        return SENDER;
    }

    public TransferType getTYPE() {
        return TYPE;
    }

    public int getAMOUNT() {
        return AMOUNT;
    }

    public enum TransferType {
        INCOME,
        OUTCOME
    }

    public Transaction(User recipient, User sender, TransferType transferType, int transferAmount, UUID ID) {
        this.RECIPIENT = recipient;
        this.SENDER = sender;
        this.TYPE = transferType;
        this.AMOUNT = transferAmount;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + ID +
                ", recipient=" + RECIPIENT +
                ", sender=" + SENDER +
                ", transferType=" + TYPE +
                ", transferAmount=" + AMOUNT +
                '}';
    }
}

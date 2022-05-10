package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User Adam = new User("Adam", 400);
        User Eva = new User("Eva", 200);

        Transaction toAdam = new Transaction(Adam, Eva, Transaction.TransferType.INCOME, 150);
        Transaction toEva = new Transaction(Eva, Adam, Transaction.TransferType.OUTCOME, 150);

        Adam.addTransaction(toAdam);
        Adam.addTransaction(toEva);

        System.out.println("All transactions of Adam");
        for (int i = 0; i < Adam.transactionsArray().length; i++)
            System.out.println(Adam.transactionsArray()[i]);

        Adam.deleteTransaction(toAdam.getId());

        System.out.println("After delete operation");

        for (int i = 0; i < Adam.transactionsArray().length; i++)
            System.out.println(Adam.transactionsArray()[i]);

        System.out.println("Delete not existing ID");
        try {
            Adam.deleteTransaction(UUID.randomUUID());
        } catch (TransactionNotFoundException e) {
            e.printStackTrace();
        }
    }
}

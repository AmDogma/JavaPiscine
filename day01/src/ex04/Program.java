package ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();
        User Adam = new User("Adam", 400);
        User Eva = new User("Eva", 200);
        service.addUser(Adam);
        service.addUser(Eva);
        try {
            service.transaction(Adam.getId(), Eva.getId(), 200);
            service.transaction(Eva.getId(), Adam.getId(), 100);
            service.transaction(Adam.getId(), Eva.getId(), 211);
            service.transaction(Eva.getId(), Adam.getId(), 99);

            System.out.println("Not valid");
            service.transaction(Adam.getId(), Eva.getId(), 800);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        System.out.println("Adam balance " + service.getBalance(Adam.getId()));
        System.out.println("Eva balance " + service.getBalance(Eva.getId()));
        System.out.println("All transactions of Adam");
        for (Transaction t :  service.getTransactions(Adam.getId()))
            System.out.println(t.getRECIPIENT().getName() + " " + t.getId() + " " + t.getTYPE());
        System.out.println("All transactions of Eva");
        for (Transaction t : service.getTransactions(Eva.getId()))
            System.out.println(t.getRECIPIENT().getName() + " " + t.getId() + " " + t.getTYPE());

        try {
            System.out.println("Valid transaction of Adam");
            Adam.deleteTransaction(Adam.transactionsArray()[0].getId());

            System.out.println("Not valid transaction of Adam");
            Adam.deleteTransaction(UUID.randomUUID());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("All transactions of Adam after delete");
        for (Transaction t :  service.getTransactions(Adam.getId()))
            System.out.println(t.getRECIPIENT().getName() + " " + t.getId() + " " + t.getTYPE());

        System.out.println("Not found pair transaction");
        for (Transaction t : service.unpairedTransactions())
            System.out.println(t.getRECIPIENT().getName() + " " + t.getId() + " " + t.getTYPE());
    }
}

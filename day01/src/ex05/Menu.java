package ex05;

import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    final int EXIT;
    final TransactionsService service = new TransactionsService();
    final Scanner SC = new Scanner(System.in);

    public Menu(int exit) {
        this.EXIT = exit;
    }

    public void start() {
        int i = show();
        while (i != EXIT) {
            if (i >= 1 && i < EXIT) {
                option(i);
                System.out.println("---------------------------------------------------------");
            }
            i = show();
        }
        System.out.println("Bye!");
    }

    private void option(int i) {
        if (i == 1)
            addUser();
        else if (i == 2)
            showBalance();
        else if (i == 3)
            transfer();
        else if (i == 4)
            showUserTransactions();
        else if (i == 5)
            removeTransfer();
        else if (i == 6)
            checkTransfer();
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        System.out.print("-> ");
        String name = SC.next();
        try {
            User add = new User(name, Integer.parseInt(SC.nextLine().trim()));
            service.addUser(add);
            System.out.printf("User with id = %d is added%n", add.getId());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void showBalance() {
        System.out.println("Enter a user ID");
        System.out.print("-> ");
        try {
            User user = service.getUsersList().getById(Integer.parseInt(SC.nextLine()));
            System.out.printf("%s - %d%n", user.getName(), user.getBalance());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void transfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        System.out.print("-> ");
        String[] args = SC.nextLine().split(" ");
        if (args.length == 3) {
            try {
                service.transaction(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                System.out.println("The transfer is completed");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

    }

    private void showUserTransactions() {
        System.out.println("Enter a user ID");
        System.out.print("-> ");
        try {
            User user = service.getUsersList().getById(Integer.parseInt(SC.nextLine()));
            for (Transaction i : service.getTransactions(user.getId())) {
                String type = (i.getTYPE() == Transaction.TransferType.INCOME ? "From" : "To");
                User toPrint = (i.getRECIPIENT() == user ? i.getSENDER() : i.getRECIPIENT());
                System.out.printf("%s %s(id = %d) %d with id = %s%n", type, toPrint.getName(),
                        toPrint.getId(), (type.equals("To") ? -i.getAMOUNT() : i.getAMOUNT()), i.getId());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void removeTransfer() {
        System.out.println("Enter a user ID and a transfer ID");
        System.out.print("-> ");
        try {
            User user = service.getUsersList().getById(SC.nextInt());
            UUID ID = UUID.fromString(SC.nextLine().trim());
            System.out.println(ID);
            Transaction t = service.findUserTransaction(user.getId(), ID);
            String type = (t.getTYPE() == Transaction.TransferType.INCOME ? "From" : "To");
            User toPrint = (t.getRECIPIENT() == user ? t.getSENDER() : t.getRECIPIENT());
            service.removeTransaction(user.getId(), ID);
            System.out.printf("Transfer %s %s(id = %d) %d removed%n",
                    type, toPrint.getName(), toPrint.getId(), t.getAMOUNT());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void checkTransfer() {
        System.out.println("Check results:");
        try {
            for (Transaction unpaired : service.unpairedTransactions()) {
                User firstName = null;
                User secondName = null;
                for (int userIndex = 0; userIndex < service.getUsersList().size(); userIndex++) {
                    for (Transaction g : service.getUsersList().getByIndex(userIndex).transactionsArray()) {
                        if (g.equals(unpaired)) {
                            firstName = service.getUsersList().getByIndex(userIndex);
                            secondName = (unpaired.getRECIPIENT().equals(firstName)
                                    ? unpaired.getSENDER() : unpaired.getRECIPIENT());
                        }
                    }
                }
                if (Objects.isNull(firstName) || Objects.isNull(secondName))
                        throw new NullPointerException();
                String type = (unpaired.getTYPE() == Transaction.TransferType.INCOME ? "To" : "From");
                System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s %s %s(id = %d) for %d%n",
                        firstName.getName(), firstName.getId(), unpaired.getId(), type,
                        secondName.getName(), secondName.getId(), unpaired.getAMOUNT());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private int show() {
        printMenu();
        try {
            return Integer.parseInt(SC.nextLine());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void printMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (EXIT == 7) {
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
            System.out.println("7. Finish execution");
        }
        else
            System.out.println("5. Finish execution");
        System.out.print("-> ");
    }
}

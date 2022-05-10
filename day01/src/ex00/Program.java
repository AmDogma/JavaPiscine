package ex00;

public class Program {
    public static void main(String[] args) {
        User Adam = new User("Adam", 1, 400);
        User Eva = new User("Eva", 2, -200);
        Transaction toAdam = new Transaction(Adam, Eva, Transaction.TransferType.INCOME, 150);
        Transaction toEva = new Transaction(Eva, Adam, Transaction.TransferType.INCOME, 150);
        System.out.println(toAdam);
        System.out.println(toEva);
        Transaction toEva2 = new Transaction(Eva, null, Transaction.TransferType.INCOME, 150);
    }
}

package ex05;

public class TransactionNotFoundException extends RuntimeException {

    @Override
    public String toString() {
        return "Transaction Not Found";
    }
}

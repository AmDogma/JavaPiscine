package ex04;

public class TransactionNotFoundException extends RuntimeException {

    @Override
    public String toString() {
        return "Transaction Not Found";
    }
}

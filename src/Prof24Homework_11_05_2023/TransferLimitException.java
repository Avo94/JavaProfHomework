package Prof24Homework_11_05_2023;

public class TransferLimitException extends NotEnoughFunds{
    public TransferLimitException(String message) {
        super(message);
    }
}
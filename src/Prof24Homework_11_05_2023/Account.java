package Prof24Homework_11_05_2023;

import java.util.regex.Pattern;

public class Account {

    private String iban;

    private String confirmationNumber;

    private int balance;

    public Account(String iban, String confirmationNumber, int balance) {
        this.iban = iban;
        this.confirmationNumber = confirmationNumber;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static boolean isValidIBAN(String iban) throws IncorrectIbanNumber {
        Pattern pattern = Pattern.compile("(\\w{2}\\d{32})");
        return pattern.matcher(iban).matches();
    }

    public static boolean isValidConfirmationNumber(String number) throws IncorrectCodeNTemplate {
        Pattern pattern = Pattern.compile("(\\d{6})");
        return pattern.matcher(number).matches();
    }
}

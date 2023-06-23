package Prof24Homework_11_05_2023;

import java.util.Scanner;

public class AccountService {

    private static final int TRANSFER_LIMIT = 10000;
    private static String retry = "no";

    public static String ibanCheck(Account account, String iban) {
        retry = "no";
        try {
            if (!account.isValidIBAN(iban)) {
                if (!account.getIban().equals(iban)) {
                    throw new WrongIbanNumber("Введенный IBAN не совпадает с IBAN'ом выбранного аккаунта.");
                }
                throw new IncorrectIbanNumber("Введеный IBAN: " + iban.toUpperCase() +
                        " - не соответствует заданному формату.");
            }
        } catch (IncorrectIbanNumber e) {
            System.out.println(e.getMessage());
            retry = "yes";
        } catch (WrongIbanNumber e) {
            System.out.println(e.getMessage());
            System.out.println("Чтобы повторить попытку введите слово yes, " +
                    "при любом другом дейстии приложение завершит свою работу.");
            retry = new Scanner(System.in).nextLine();
        }
        return retry;
    }

    public static String confirmationNumberCheck(Account account, String confirmNumber) {
        retry = "no";
        try {
            if (!Account.isValidConfirmationNumber(confirmNumber)) {
                if (!account.getConfirmationNumber().equals(confirmNumber)) {
                    throw new WrongAccessCode("Введенный код не совпадает с кодом подтверждения выбранного аккаунта.");
                }
                throw new IncorrectCodeNTemplate("Введеный код: " + confirmNumber +
                        " - не соответствует заданному формату.");
            }
        } catch (IncorrectCodeNTemplate e) {
            System.out.println(e.getMessage());
            retry = "yes";
        } catch (WrongAccessCode e) {
            System.out.println(e.getMessage());
            System.out.println("Чтобы повторить попытку введите слово yes, " +
                    "при любом другом дейстии приложение завершит свою работу.");
            retry = new Scanner(System.in).nextLine();
        }
        return retry;
    }

    public static String transactionSumCheck(Account account, int sum) {
        retry = "no";
        try {
            if (sum < 0 || sum > account.getBalance() || sum > TRANSFER_LIMIT) {
                if (sum > TRANSFER_LIMIT && account.getBalance() > TRANSFER_LIMIT) {
                    throw new TransferLimitException("Вы пытаетесь перевести больше 10000$");
                }
                throw new NotEnoughFunds("Введенная сумма выходит за рамки допустимой");
            }
        } catch (TransferLimitException e) {
            System.out.println(e.getMessage());
            retry = "yes";
        } catch (NotEnoughFunds e) {
            System.out.println(e.getMessage());
            System.out.println("Чтобы повторить попытку введите слово yes, " +
                    "при любом другом дейстии приложение завершит свою работу.");
            retry = new Scanner(System.in).nextLine();
        }
        return retry;
    }

    public static String wrongChoiceCheck(int accountPositionNumber, int collectionSize) {
        retry = "no";
        try {
            if (accountPositionNumber < 1 || accountPositionNumber > collectionSize) {
                throw new WrongOptionSelected("Вы сделали некорретный выбор");
            }
        } catch (WrongOptionSelected e) {
            System.out.println(e.getMessage());
            retry = "yes";
        }
        return retry;
    }
}
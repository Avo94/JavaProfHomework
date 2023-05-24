package Prof24Homework_11_05_2023;

import java.util.Scanner;

public class AccountService {

    private static String retry = "no";

    public static String ibanCheck(Account account, String iban) {
        retry = "no";
        try {
            if (!account.getIban().equals(iban)) {
                if (!account.isValidIBAN(iban)) {
                    throw new IncorrectIbanNumber("Введеный IBAN: " + iban.toUpperCase() +
                            " - не соответствует заданному формату.");
                }
                throw new WrongIbanNumber("Введенный IBAN не совпадает с IBAN'ом выбранного аккаунта.");
            }
        } catch (IncorrectIbanNumber e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            retry = "yes";
        } catch (WrongIbanNumber e) {
            e.printStackTrace();
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
            if (!account.getConfirmationNumber().equals(confirmNumber)) {
                if (!Account.isValidConfirmationNumber(confirmNumber)) {
                    throw new IncorrectCodeNTemplate("Введеный код: " + confirmNumber +
                            " - не соответствует заданному формату.");
                }
                throw new WrongAccessCode("Введенный код не совпадает с кодом подтверждения выбранного аккаунта.");
            }
        } catch (IncorrectCodeNTemplate e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            retry = "yes";
        } catch (WrongAccessCode e) {
            e.printStackTrace();
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
            if (sum < 0 || sum > account.getBalance() || sum > 10000) {
                if (sum > 10000 && account.getBalance() > 10000) {
                    throw new TransferLimitException("Вы пытаетесь перевести больше 10000$");
                }
                throw new NotEnoughFunds("Введенная сумма выходит за рамки допустимой");
            }
        } catch (TransferLimitException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            retry = "yes";
        } catch (NotEnoughFunds e) {
            e.printStackTrace();
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
            e.printStackTrace();
            System.out.println(e.getMessage());
            retry = "yes";
        }
        return retry;
    }
}
package Prof24Homework_11_05_2023;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TransactionApp {
    public static void main(String[] args) {

        Account accountOne =
                new Account("RR24513200578190013325911100000845", "375029", 50000);
        Account accountTwo =
                new Account("IB24332005214000107893225411110001", "112104", 2450);
        List<Account> accounts = Arrays.asList(accountOne, accountTwo);
        AccountData accountData = new AccountData(accounts);

        System.out.println("В системе найдено счетов: " + accounts.size());
        accountData.getAccounts().keySet().stream().map(x -> "****" + x.substring(x.length() - 4))
                .forEach(System.out::println);

        int accountPosition = 0;
        Account senderAccount = null;
        Account recipientAccount = null;
        String retry;

        retry = "yes";
        while ("yes".equals(retry)) {
            System.out.println("Введите порядковый номер счёта для авторизации:");
            accountPosition = new Scanner(System.in).nextInt() - 1;
            retry = AccountService.wrongChoiceCheck(accountPosition, accountData.getAccounts().size());
        }

        int counter = 0;
        for (Account account : accounts) {
            if (counter == accountPosition) senderAccount = account;
            counter++;
        }

        String iban;
        int countOfFails;
        do {
            countOfFails = 0;
            do {
                iban = inputNUmber();
                retry = AccountService.ibanCheck(senderAccount, iban);
            } while ("yes".equals(retry));
            endOfProgramCheck(retry);

            do {
                if (countOfFails > 2) break;
                System.out.println("Введите код подтверждения (6 цифр) для счёта " + iban + ":");
                String confirmNumber = inputNumber(iban);
                retry = AccountService.confirmationNumberCheck(senderAccount, confirmNumber);
                countOfFails++;
            } while ("yes".equals(retry));
            endOfProgramCheck(retry);
        } while (countOfFails > 2);

        System.out.println("Доступные суммы для перевода:");
        accounts.stream().map(x -> "Счёт: ****" + x.getIban().
                        substring(x.getIban().length() - 4) + " - " + x.getBalance() + "$")
                .forEach(System.out::println);

        accountPosition = 0;
        retry = "yes";
        while ("yes".equals(retry)) {
            System.out.println("Введите порядковый номер счёта, с которого хотите перевести деньги на другой счёт:");
            accountPosition = new Scanner(System.in).nextInt() - 1;
            retry = AccountService.wrongChoiceCheck(accountPosition, accounts.size());
        }

        int recipientAccountPosition;
        do {
            System.out.println("Выберете номер аккаунта, а который хотите перевести деньги");
            recipientAccountPosition = new Scanner(System.in).nextInt() - 1;
            if (recipientAccountPosition == accountPosition) {
                System.out.println("Вы должны выбрать счёт отличный от текущего");
            }
        } while (recipientAccountPosition == accountPosition);

        counter = 0;
        for (Account account : accounts) {
            if (counter == accountPosition) {
                senderAccount = account;
            }
            if (counter == recipientAccountPosition) {
                recipientAccount = account;
            }
            counter++;
        }

        int sum;
        do {
            System.out.println("Введите сумму $ для перевода:");
            sum = new Scanner(System.in).nextInt();
            retry = AccountService.transactionSumCheck(senderAccount, sum);
        } while ("yes".equals(retry));
        endOfProgramCheck(retry);

        senderAccount.setBalance(senderAccount.getBalance() - sum);
        recipientAccount.setBalance(recipientAccount.getBalance() + sum);

        System.out.println("Введите слово yes, если хотите увидеть актуальный баланс. Либо нажмите Enter");
        if ("yes".equals(new Scanner(System.in).nextLine())) {
            accounts.stream().map(x -> "Счёт: ****" + x.getIban().
                            substring(x.getIban().length() - 4) + " - " + x.getBalance() + "$")
                    .forEach(System.out::println);
        }
    }

    public static String inputNUmber() {
        System.out.println("Введите полный IBAN-номер," +
                "выбранного банковского счёта (2 латинские буквы + 32 цифры):");
        return new Scanner(System.in).nextLine();
    }

    public static String inputNumber(String iban) {
        System.out.println("Введите код подтверждения (6 цифр) для счёта " + iban + ":");
        return new Scanner(System.in).nextLine();
    }

    public static void endOfProgramCheck(String retry) {
        if (!"no".equals(retry)) {
            System.exit(0);
        }
    }
}
package Prof24Homework_11_05_2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountData {

    private final Map<String, String> accounts = new HashMap<>();

    public AccountData(List<Account> accounts) {
        for (Account account : accounts) {
            this.accounts.put(account.getIban(), account.getConfirmationNumber());
        }
    }

    public Map<String, String> getAccounts() {
        return this.accounts;
    }
}
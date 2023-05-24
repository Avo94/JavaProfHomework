package Prof24Homework_11_05_2023;

import java.util.HashMap;
import java.util.Map;

public class AccountData {

    private Map<String, String> accounts;

    public AccountData() {
        this.accounts = new HashMap<>();
    }

    public void setAccounts(Account account) {
        accounts.put(account.getIban(), account.getConfirmationNumber());
    }
}

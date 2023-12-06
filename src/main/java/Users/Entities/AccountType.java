package Users.Entities;

import java.util.ArrayList;

public enum AccountType {
    BANK_USER(1, "bankAccount"),
    WALLET_USER(2, "walletAccount");
    private final int index;
    private final String account;

    AccountType(int index, String account) {
        this.index = index;
        this.account = account;
    }

    public int getIndex() {
        return index;
    }

    public String toString() {
        return account;
    }

    public ArrayList<String> getUserTypes() {
        ArrayList<String> userTypes = new ArrayList<>();
        for (AccountType accountType : AccountType.class.getEnumConstants()) {
            userTypes.add(accountType.account);
        }
        return userTypes;
    }
}

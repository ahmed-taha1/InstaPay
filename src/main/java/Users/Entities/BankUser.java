package Users.Entities;

public class BankUser implements IUser{
    private InstaPayAccount instaPayAccount;
    private final String bankAccount;

    public BankUser(InstaPayAccount instaPayAccount, String bankAccount) {
        this.instaPayAccount = instaPayAccount;
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    @Override
    public InstaPayAccount getInstaPayAccount() {
        return instaPayAccount;
    }
}

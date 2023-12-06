package Users.Entities;

public class WalletUser implements IUser{
    private InstaPayAccount instaPayAccount;
    public WalletUser(InstaPayAccount instaPayAccount) {
        this.instaPayAccount = instaPayAccount;
    }
    @Override
    public InstaPayAccount getInstaPayAccount() {
        return instaPayAccount;
    }
}

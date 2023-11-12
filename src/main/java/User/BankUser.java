package User;

public class BankUser {
    private final UserProfile userProfile;
    private final String accountNumber;

    BankUser(UserProfile userProfile,String accountNumber){
        this.userProfile=userProfile;
        this.accountNumber=accountNumber;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

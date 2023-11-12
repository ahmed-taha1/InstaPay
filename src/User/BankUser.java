package User;

public class BankUser {
    protected UserProfile userProfile;
    protected String accountNumber;

    BankUser(UserProfile userProfile,String accountNumber){
        this.userProfile=userProfile;
        this.accountNumber=accountNumber;
    }
}

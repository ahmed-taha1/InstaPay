package Users.Entities;

public class InstaPayAccount {
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private final AccountType accountType;
    private final String accountProvider;

    public InstaPayAccount(String userName, String password, String email, String phoneNumber,AccountType accountType,String accountProvider) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
        this.phoneNumber = phoneNumber;
        this.accountProvider = accountProvider;
    }
    public InstaPayAccount(AccountType accountType,String accountProvider){
        this.accountProvider = accountProvider;
        this.accountType = accountType;
    }
    public InstaPayAccount(AccountType accountType,String accountProvider,String phoneNumber){
        this.accountProvider = accountProvider;
        this.accountType = accountType;
        this.phoneNumber = phoneNumber;
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountProvider() {
        return accountProvider;
    }
}

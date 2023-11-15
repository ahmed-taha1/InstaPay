package InstaPay.SW.spring_boot_instapay_project.Users.Entities;

public class UserProfile {
    private final String userName;
    private final String password;
    private final String email;
    private final String phoneNumber;
    private final String userType;

    public UserProfile(String userName, String password, String email, String userType, String phoneNumber){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this. userType = userType;
        this. phoneNumber = phoneNumber;
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
    public String getUserType() {
        return userType;
    }

    public Object getUserEntity(String number) {
        if (userType=="BankUser") {
            return new BankUser(this, number);
        } else if (userType=="WalletUser") {
            return new WalletUser(this);
        } else {
            return ("Invalid user type");
        }
    }
}

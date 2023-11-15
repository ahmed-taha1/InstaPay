package InstaPay.SW.spring_boot_instapay_project.Users.Entities;

public abstract class User {
    private final String userName;
    private final String password;
    private final String phoneNumber;
    protected String userType;

    public User(String userName, String password, String phoneNumber){
        this.userName = userName;
        this.password = password;
        this. phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getUserType() {
        return userType;
    }
}

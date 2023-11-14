package Users.Entities;

public class UserProfile {
    private final String userName;
    private final String password;
    private final String phoneNumber;
    private final String userType;
    private boolean isVerified;
    UserProfile(String userName, String password, String email, String userType, String phoneNumber){
        this.userName = userName;
        this.password = password;
        this. userType = userType;
        this. phoneNumber = phoneNumber;
        this.isVerified = false;
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
    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}

package User;

import java.util.*;

public class UserProfile {
    protected String userName;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected String userType;
//    public static Vector<UserProfile> userProfiles = new Vector<>();

    UserProfile(String userName, String password, String email, String userType,String phoneNumber){
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
//    public Vector<UserProfile> getUsers() {
//        return userProfiles;
//    }
    public String getUserType() {
        return userType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUserType(String userType) {
        this. userType = userType;
    }
    

}

package User;

import java.util.*;

public class UserProfile {
    protected String userName;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected boolean userType;
    public static Vector<UserProfile> userProfiles = new Vector<>();

    UserProfile(String userName, String password, String email, boolean userType,String phoneNumber){
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
    public Vector<UserProfile> getUsers() {
        return userProfiles;
    }
    public boolean getUserType() {
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
    public void setUserType(Boolean userType) {
        this. userType = userType;
    }
    public String signUp (String userName, String email){
        String valid ="yes";
        for(int i = 0; i< userProfiles.size(); i++){
            UserProfile FU = userProfiles.get(i);
            String UN = FU.getUserName();
            String E = FU.getEmail();
            if(userName.equals(UN) || email.equals(E)){
                System.out.println("user already exist (name or email)");
                valid = "no";
                break;
            }
        }
        return valid;
    }

    public String signIn (String userName ,String password){
        String userTypeReturn="";
        boolean b=true;


        for(int i = 0; i< userProfiles.size(); i++){
            UserProfile FU = userProfiles.get(i);
            String UN = FU.getUserName();
            String p = FU.getPassword();
            if(userName.equals(UN)  && password.equals(p) ){
                b=FU.getUserType();
                userTypeReturn="yes";
                break;
            }
        }

        if(userTypeReturn=="yes"){
            if(b==true){
                return "admin";
            }else{
                return "user";
            }
        } //System.out.println("Singed in successfully");
        else{
            System.out.println("invalid user name or password");
            return "not found";
        }
    }
    

}

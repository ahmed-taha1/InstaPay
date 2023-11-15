package InstaPay.SW.spring_boot_instapay_project.Users.Entities;

public class WalletUser  {
    private final UserProfile userProfile;
    WalletUser(UserProfile userProfile){
        this.userProfile=userProfile;
    }
    public String getPhoneNumber(){
        return this.userProfile.getPhoneNumber();
    }
}

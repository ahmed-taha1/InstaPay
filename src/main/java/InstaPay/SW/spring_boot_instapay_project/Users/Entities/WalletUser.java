package InstaPay.SW.spring_boot_instapay_project.Users.Entities;

public class WalletUser  extends User{
    private String walletProvider;
    public WalletUser(String userName, String password, String phoneNumber, String walletProvider){
        super(userName, password, phoneNumber);
        this.walletProvider = walletProvider;

        userType = "wallet";
    }

    public String getWalletProvider() {
        return walletProvider;
    }
}
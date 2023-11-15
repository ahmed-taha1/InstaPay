package InstaPay.SW.spring_boot_instapay_project.Users.Entities;

import java.util.Map;

public class BankUser extends User{
    private final String bankAccountNumber;

    public BankUser(String userName, String password, String phoneNumber, String bankAccountNumber){
        super(userName, password, phoneNumber);
        this.bankAccountNumber = bankAccountNumber;

        userType = "bank";
    }
    public String getAccountNumber() {
        return bankAccountNumber;
    }
}
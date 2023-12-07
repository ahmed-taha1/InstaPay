package Users.DataAccess;

import Users.Entities.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUsers implements IUserDataAccess {
    private List<IUser>mockUsersDB = new ArrayList<>();
    public InMemoryUsers(){
        mockUsersDB = new ArrayList<>();
        mockUsersDB.add(new WalletUser(
                new InstaPayAccount(
                        "ismail",
                        "ismail",
                        "ismailmagdy88@gmail.com",
                        "01157077022",
                        AccountType.walletAccount,
                        "mockWallet"
                        )
                )
        );
        mockUsersDB.add(new BankUser(
                        new InstaPayAccount(
                                "taha",
                                "taha",
                                "taha@gmail.com",
                                "01120293048",
                                AccountType.bankAccount,
                                "mockBank"
                        ),"20210033"
                )
        );
    }
    @Override
    public IUser getUserByMobileNumber(String phoneNumber) {
        for(var user : mockUsersDB){
            if(user.getInstaPayAccount().getPhoneNumber().equals(phoneNumber)){
                return user;
            }
        }
        return null;
    }

    @Override
    public IUser getUserByUserName(String userName) {
        for(var user : mockUsersDB){
            if(user.getInstaPayAccount().getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    @Override
    public BankUser getUserByBankAccount(String bankAccount) {
        for(var user : mockUsersDB){
            if(user.getInstaPayAccount().getAccountType() != AccountType.bankAccount){
                continue;
            }
            if(((BankUser)user).getBankAccount().equals(bankAccount)){
                return (BankUser) user;
            }
        }
        return null;
    }
}

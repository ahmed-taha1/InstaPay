package Users.DataAccess;

import Users.Entities.BankUser;
import Users.Entities.IUser;
import Users.Entities.InstaPayAccount;

public class InMemoryDataAccess implements IUserDataAccess {
    @Override
    public IUser getUserByMobileNumber(String phoneNumber) {
        return null;
    }

    @Override
    public IUser getUserByUserName(String Name) {
        return null;
    }

    @Override
    public BankUser getUserByBankAccount(String bankAccount) {
        return null;
    }
}

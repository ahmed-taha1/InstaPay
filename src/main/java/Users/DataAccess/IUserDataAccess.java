package Users.DataAccess;

import Users.Entities.BankUser;
import Users.Entities.IUser;
import Users.Entities.InstaPayAccount;

public interface IUserDataAccess {
    IUser getUserByMobileNumber(String phoneNumber);
    IUser getUserByUserName(String Name);
    BankUser getUserByBankAccount(String bankAccount);
}

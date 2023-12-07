package AccountProviderGateways.BankGateways;

import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import AccountProviderGateways.IAccountProviderGateway;
import Users.Entities.AccountType;
import Users.Entities.BankUser;
import Users.Entities.IUser;

public class MockBankAccountProviderGateway implements IAccountProviderGateway {

    @Override
    public void depositMoney(IUser user,double amount) throws CustomException {
        if(user.getInstaPayAccount().getAccountType() != AccountType.bankAccount){
            throw new CustomException(StatusCodes.UNAUTHORIZED,"User Is not a bankUser");
        }
        BankUser bankUser = (BankUser) user;
        if (!MockBankDB.findUserByBankAccount(bankUser.getBankAccount())) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Bank account doesn't exist in mockBank");
        }
        Double userBalance = MockBankDB.getUserBalance(bankUser.getBankAccount());
        MockBankDB.updateUserBalance(bankUser.getBankAccount(), userBalance + amount);
    }

    public void withdrawMoney(IUser user,double amount) throws CustomException {
        if(user.getInstaPayAccount().getAccountType() != AccountType.bankAccount){
            throw new CustomException(StatusCodes.UNAUTHORIZED,"User Is not a bankUser");
        }
        BankUser bankUser = (BankUser) user;
        if (!MockBankDB.findUserByBankAccount(bankUser.getBankAccount())) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Bank account doesn't exist in mockBank");
        }
        Double userBalance = MockBankDB.getUserBalance(bankUser.getBankAccount());
        if (userBalance < amount) {
            throw new CustomException(StatusCodes.FORBIDDEN, "Insufficeint balance In MOCKBANK");
        }
        MockBankDB.updateUserBalance(bankUser.getBankAccount(), userBalance - amount);
    }

    @Override
    public double getBalance(IUser user) throws CustomException {
        if(user.getInstaPayAccount().getAccountType() != AccountType.bankAccount){
            throw new CustomException(StatusCodes.UNAUTHORIZED,"User Is not a bankUser");
        }
        BankUser bankUser = (BankUser) user;
        return MockBankDB.getUserBalance(bankUser.getBankAccount());
    }
}
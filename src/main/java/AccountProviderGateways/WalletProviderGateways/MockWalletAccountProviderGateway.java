package AccountProviderGateways.WalletProviderGateways;

import Exceptions.CustomException;
import AccountProviderGateways.IAccountProviderGateway;
import StatusCodes.StatusCodes;
import Users.Entities.IUser;

public class MockWalletAccountProviderGateway implements IAccountProviderGateway {

    @Override
    public void depositMoney(IUser user,double amount) throws CustomException {
        if (!MockWalletDB.findUser(user.getInstaPayAccount().getPhoneNumber())) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Wallet account doesn't exist in MockWallet");
        }
        Double userBalance = MockWalletDB.getUserBalance(user.getInstaPayAccount().getPhoneNumber());
        MockWalletDB.updateUserBalance(user.getInstaPayAccount().getPhoneNumber(), userBalance + amount);
    }

    public void withdrawMoney(IUser user,double amount) throws CustomException {
        if (!MockWalletDB.findUser(user.getInstaPayAccount().getPhoneNumber())) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Wallet account doesn't exist in MockWallet");
        }
        Double userBalance = MockWalletDB.getUserBalance(user.getInstaPayAccount().getPhoneNumber());
        if (userBalance < amount) {
            throw new CustomException(StatusCodes.FORBIDDEN, "Insufficeint balance In MockWallet");
        }
        MockWalletDB.updateUserBalance(user.getInstaPayAccount().getPhoneNumber(), userBalance - amount);
    }
    @Override
    public double getBalance(IUser user) throws CustomException {
        return MockWalletDB.getUserBalance(user.getInstaPayAccount().getPhoneNumber());
    }
}
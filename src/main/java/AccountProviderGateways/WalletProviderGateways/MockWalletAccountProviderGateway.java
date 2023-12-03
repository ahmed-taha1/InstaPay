package AccountProviderGateways.WalletProviderGateways;

import Exceptions.CustomException;
import AccountProviderGateways.IAccountProviderGateway;
import StatusCodes.StatusCodes;

public class MockWalletAccountProviderGateway implements IAccountProviderGateway {
    private final String userPhoneNumber;

    public MockWalletAccountProviderGateway(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    @Override
    public void depositMoney(double amount) throws CustomException {
        if (!MockWalletDB.findUser(userPhoneNumber)) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Wallet account doesn't exist in MockWallet");
        }
        Double userBalance = MockWalletDB.getUserBalance(this.userPhoneNumber);
        MockWalletDB.updateUserBalance(this.userPhoneNumber, userBalance + amount);
    }

    public void withdrawMoney(double amount) throws CustomException {
        if (!MockWalletDB.findUser(userPhoneNumber)) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Wallet account doesn't exist in MockWallet");
        }
        Double userBalance = MockWalletDB.getUserBalance(this.userPhoneNumber);
        if (userBalance < amount) {
            throw new CustomException(StatusCodes.FORBIDDEN, "Insufficeint balance In MockWallet");
        }
        MockWalletDB.updateUserBalance(this.userPhoneNumber, userBalance - amount);
    }

    @Override
    public double getBalance() throws CustomException {
        return MockWalletDB.getUserBalance(this.userPhoneNumber);
    }
}
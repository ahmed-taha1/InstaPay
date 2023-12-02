package PaymentGateways.WalletProviderGateways;
import Exceptions.CustomException;
import PaymentGateways.IPaymentGateway;
import StatusCodes.StatusCodes;
public class MockWalletPaymentGateway implements IPaymentGateway {
    private final String userPhoneNumber;
    public MockWalletPaymentGateway(String userPhoneNumber){
        this.userPhoneNumber = userPhoneNumber;
    }
    @Override
    public void depositMoney(double amount) throws CustomException {
        if(!MockWalletDB.findUser(userPhoneNumber)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Wallet account doesn't exist in MockWallet");
        }
        Double userBalance = MockWalletDB.getUserBalance(this.userPhoneNumber);
        MockWalletDB.updateUserBalance(this.userPhoneNumber,userBalance + amount);
    }
    public void withdrawMoney(double amount) throws CustomException{
        if(!MockWalletDB.findUser(userPhoneNumber)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Wallet account doesn't exist in MockWallet");
        }
        Double userBalance = MockWalletDB.getUserBalance(this.userPhoneNumber);
        if(userBalance < amount){
            throw new CustomException(StatusCodes.FORBIDDEN,"Insufficeint balance In MockWallet");
        }
        MockWalletDB.updateUserBalance(this.userPhoneNumber,userBalance - amount);
    }
    @Override
    public double getBalance() throws CustomException {
        return MockWalletDB.getUserBalance(this.userPhoneNumber);
    }
}
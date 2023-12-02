package Gateways.WalletProviderGateways;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Gateways.IPaymentGateway;

import java.util.Map;

public class MockWalletPaymentGateway implements IPaymentGateway {
    private final String userPhoneNumber;
    private static Map<String,Double> mockWalletProviderGateway;
    private static void seedMockDB(){
        mockWalletProviderGateway.put("20210069",10000.0);
        mockWalletProviderGateway.put("20210033",1500.0);
        mockWalletProviderGateway.put("20210084",2200.0);
        mockWalletProviderGateway.put("20210000",5000.0);
    }
    public MockWalletPaymentGateway(String userPhoneNumber)throws CustomException {
        if(mockWalletProviderGateway.get(userPhoneNumber) == null){
            throw new CustomException(StatusCodes.NOT_FOUND,"Wallet Provider account doesn't exist in mockBank");
        }
        this.userPhoneNumber = userPhoneNumber;
    }
    @Override
    public void depositMoney(double amount) throws CustomException {
        mockWalletProviderGateway.put(this.userPhoneNumber, mockWalletProviderGateway.get(this.userPhoneNumber) + amount);
    }
    public void withdrawMoney(double amount) throws CustomException {
        if(mockWalletProviderGateway.get(userPhoneNumber) < amount){
            throw new CustomException(StatusCodes.FORBIDDEN,"User balance is not suffiecient");
        }
        mockWalletProviderGateway.put(this.userPhoneNumber, mockWalletProviderGateway.get(this.userPhoneNumber) - amount);
    }
    @Override
    public double getBalance() throws CustomException {
        return mockWalletProviderGateway.get(userPhoneNumber);
    }
}

package AccountProviderGateways.Factories;

import AccountProviderGateways.BankGateways.MockBankAccountProviderGateway;
import AccountProviderGateways.IAccountProviderGateway;
import AccountProviderGateways.WalletProviderGateways.MockWalletAccountProviderGateway;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;

import java.util.HashMap;
import java.util.Map;

public class AccountProviderGatewayGatewayFactory implements IAccountProviderGatewayFactory {
    private static AccountProviderGatewayGatewayFactory factoryInstance = null;
    private final Map<String, IAccountProviderGateway> accountProviderMapping;

    private AccountProviderGatewayGatewayFactory() {
        accountProviderMapping = new HashMap<>();
        accountProviderMapping.put("mockBank", new MockBankAccountProviderGateway());
        accountProviderMapping.put("mockWallet", new MockWalletAccountProviderGateway());
    }

    public static AccountProviderGatewayGatewayFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new AccountProviderGatewayGatewayFactory();
        }
        return factoryInstance;
    }

    public IAccountProviderGateway createAccountProviderGateway(String accountProvider) throws CustomException {
        IAccountProviderGateway accountProviderGateway = accountProviderMapping.get(accountProvider);
        if(accountProviderGateway == null){
            throw new CustomException(StatusCodes.BAD_REQUEST,accountProvider+" Account provider is not Valid");
        }
        return accountProviderGateway;
    }
}

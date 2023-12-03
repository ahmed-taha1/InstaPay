package AccountProviderGateways.Factories;

import Exceptions.CustomException;
import StatusCodes.StatusCodes;

import java.util.HashMap;
import java.util.Map;

public class AccountPaymentGatewayFactory {
    private static AccountPaymentGatewayFactory factoryInstance = null;
    private final Map<String, IAccountProviderGateway> factoriesMapping;

    private AccountPaymentGatewayFactory() {
        factoriesMapping = new HashMap<>();
        factoriesMapping.put("mockBank", new MockBankGateway());
        factoriesMapping.put("mockWallet", new MockWallet());
    }

    public static AccountPaymentGatewayFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new AccountPaymentGatewayFactory();
        }
        return factoryInstance;
    }

    public AccountProviderGateways.IAccountProviderGateway createPaymentGateway(Map<String, Object> attributes) throws CustomException {
        if (attributes.get("paymentGateway") == null || !(attributes.get("paymentGateway") instanceof String)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "paymentGateway Must be specified");
        }
        String paymentGateway = (String) attributes.get("paymentGateway");
        IAccountProviderGateway factory = factoriesMapping.get(paymentGateway);
        if (factory == null) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Invalid paymentGateway");
        }
        return factory.createPaymentGateway(attributes);
    }
}

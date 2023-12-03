package AccountProviderGateways.Factories;

import Exceptions.CustomException;
import AccountProviderGateways.BankGateways.MockBankAccountProviderGateway;
import StatusCodes.StatusCodes;

import java.util.Map;

public class MockBankGateway implements IAccountProviderGateway {
    public AccountProviderGateways.IAccountProviderGateway createPaymentGateway(Map<String, Object> attributes) throws CustomException {
        if (attributes.get("bankAccount") == null || !(attributes.get("bankAccount") instanceof String)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "User Bank account must be specified");
        }
        String bankAccount = (String) attributes.get("bankAccount");
        return new MockBankAccountProviderGateway(bankAccount);
    }
}

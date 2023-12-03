package AccountProviderGateways.Factories;

import Exceptions.CustomException;
import AccountProviderGateways.WalletProviderGateways.MockWalletAccountProviderGateway;
import StatusCodes.StatusCodes;

import java.util.Map;

public class MockWallet implements IAccountProviderGateway {
    @Override
    public AccountProviderGateways.IAccountProviderGateway createPaymentGateway(Map<String, Object> attributes) throws CustomException {
        if (attributes.get("phoneNumber") == null || !(attributes.get("phoneNumber") instanceof String)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "User Phone number must be provided");
        }
        String phoneNumber = (String) attributes.get("phoneNumber");
        return new MockWalletAccountProviderGateway(phoneNumber);
    }
}

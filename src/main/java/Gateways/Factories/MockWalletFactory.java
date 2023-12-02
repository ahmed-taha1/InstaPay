package Gateways.Factories;
import Exceptions.CustomException;
import Gateways.IPaymentGateway;
import Gateways.WalletProviderGateways.MockWalletPaymentGateway;
import StatusCodes.StatusCodes;
import java.util.Map;
public class MockWalletFactory implements IPaymentGatewayFactory{
    @Override
    public IPaymentGateway createPaymentGateway(Map<String, Object> attributes) throws CustomException {
        if(attributes.get("phoneNumber") == null || !(attributes.get("phoneNumber") instanceof String)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"User Phone number must be provided");
        }
        String phoneNumber = (String)attributes.get("phoneNumber");
        return new MockWalletPaymentGateway(phoneNumber);
    }
}

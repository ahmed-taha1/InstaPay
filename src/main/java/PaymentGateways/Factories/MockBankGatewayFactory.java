package PaymentGateways.Factories;
import Exceptions.CustomException;
import PaymentGateways.BankGateways.MockBankPaymentGateway;
import PaymentGateways.IPaymentGateway;
import StatusCodes.StatusCodes;
import java.util.Map;
public class MockBankGatewayFactory implements IPaymentGatewayFactory {
    public IPaymentGateway createPaymentGateway(Map<String, Object> attributes) throws CustomException {
        if(attributes.get("bankAccount") == null || !(attributes.get("bankAccount") instanceof String)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"User Bank account must be specified");
        }
        String bankAccount = (String) attributes.get("bankAccount");
        return new MockBankPaymentGateway(bankAccount);
    }
}

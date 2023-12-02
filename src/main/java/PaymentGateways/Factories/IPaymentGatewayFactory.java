package PaymentGateways.Factories;
import Exceptions.CustomException;
import PaymentGateways.IPaymentGateway;
import java.util.Map;
interface IPaymentGatewayFactory {
    public IPaymentGateway createPaymentGateway(Map<String, Object>attributes) throws CustomException;
}

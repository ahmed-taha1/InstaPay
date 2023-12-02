package Gateways.Factories;
import Exceptions.CustomException;
import Gateways.IPaymentGateway;
import java.util.Map;
interface IPaymentGatewayFactory {
    public IPaymentGateway createPaymentGateway(Map<String, Object>attributes) throws CustomException;
}

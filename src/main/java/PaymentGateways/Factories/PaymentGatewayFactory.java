package PaymentGateways.Factories;
import Exceptions.CustomException;
import PaymentGateways.IPaymentGateway;
import StatusCodes.StatusCodes;
import java.util.HashMap;
import java.util.Map;
public class PaymentGatewayFactory {
    private static PaymentGatewayFactory factoryInstance = null;
    private final Map<String,IPaymentGatewayFactory>factoriesMapping;
    private PaymentGatewayFactory(){
        factoriesMapping = new HashMap<>();
        factoriesMapping.put("mockBank",new MockBankGatewayFactory());
        factoriesMapping.put("mockWallet",new MockWalletFactory());
    }
    public static PaymentGatewayFactory getInstance() {
        if(factoryInstance == null){
            factoryInstance = new PaymentGatewayFactory();
        }
        return factoryInstance;
    }
    public IPaymentGateway createPaymentGateway(Map<String,Object>attributes) throws CustomException {
        if(attributes.get("paymentMethod") == null || !(attributes.get("paymentMethod") instanceof String)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"PaymentMethod Must be specified");
        }
        String paymentMethod = (String) attributes.get("paymentMethod");
        IPaymentGatewayFactory factory = factoriesMapping.get(paymentMethod);
        if(factory == null){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Invalid PaymentMethod");
        }
        return factory.createPaymentGateway(attributes);
    }
}

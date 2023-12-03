package BillPaymentGateways.Factories;
import BillPaymentGateways.Entities.IBillPaymentGateway;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import java.util.HashMap;
import java.util.Map;
public class BillGatewayFactory {
    private static BillGatewayFactory factoryInstance = null;
    private Map<String, IBillGatewayFactory>factoriesMapping;
    private BillGatewayFactory(){
        factoriesMapping = new HashMap<>();
        factoriesMapping.put("mockBillGateway",new MockBillGatewayFactory());
    }
    public static BillGatewayFactory getInstance(){
        if(factoryInstance == null){
            factoryInstance = new BillGatewayFactory();
        }
        return factoryInstance;
    }
    public IBillPaymentGateway createBillGateway(Map<String,Object>attributes) throws CustomException {
        if(attributes.get("billGateway") == null || !(attributes.get("billGateway") instanceof String)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Must specify billGateway");
        }
        String billGateway = (String)attributes.get("billGateway");
        if(factoriesMapping.get(billGateway) == null){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Invalid Bill Gateway");
        }
        return factoriesMapping.get(billGateway).createBillGateway(attributes);
    }
}

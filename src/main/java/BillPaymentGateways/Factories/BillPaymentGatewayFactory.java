package BillPaymentGateways.Factories;

import BillPaymentGateways.Gateways.IBillPaymentGateway;
import BillPaymentGateways.Gateways.MockBillPaymentGateway;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;

import java.util.HashMap;
import java.util.Map;

public class BillPaymentGatewayFactory implements IBillPaymentGatewayFactory{
    private static BillPaymentGatewayFactory factoryInstance = null;
    private final Map<String, IBillPaymentGateway> gatewayMapping;

    private BillPaymentGatewayFactory() {
        gatewayMapping = new HashMap<>();
        gatewayMapping.put("mockBill", new MockBillPaymentGateway());
    }

    public static BillPaymentGatewayFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new BillPaymentGatewayFactory();
        }
        return factoryInstance;
    }

    public IBillPaymentGateway createBillGateway(String billType) throws CustomException {
        if (gatewayMapping.get(billType) == null) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Invalid Bill Type");
        }
        return gatewayMapping.get(billType);
    }
}

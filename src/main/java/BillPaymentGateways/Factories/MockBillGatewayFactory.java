package BillPaymentGateways.Factories;

import BillPaymentGateways.Entities.IBillPaymentGateway;
import BillPaymentGateways.Entities.MockBillPaymentGateway;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;

import java.util.Map;

class MockBillGatewayFactory implements IBillGatewayFactory {
    @Override
    public IBillPaymentGateway createBillGateway(Map<String, Object> attributes) throws CustomException {
        if (attributes.get("phoneNumber") == null || !(attributes.get("phoneNumber") instanceof String)) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Must specify Phone Number to pay the bill");
        }
        String phoneNumber = (String) attributes.get("phoneNumber");
        return new MockBillPaymentGateway(phoneNumber);
    }
}

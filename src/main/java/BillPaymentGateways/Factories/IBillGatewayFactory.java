package BillPaymentGateways.Factories;

import BillPaymentGateways.Entities.IBillPaymentGateway;
import Exceptions.CustomException;

import java.util.Map;

interface IBillGatewayFactory {
    public IBillPaymentGateway createBillGateway(Map<String, Object> attributes) throws CustomException;
}

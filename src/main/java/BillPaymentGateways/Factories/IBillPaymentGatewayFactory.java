package BillPaymentGateways.Factories;

import BillPaymentGateways.Gateways.IBillPaymentGateway;
import Exceptions.CustomException;

import java.util.Map;

public interface IBillPaymentGatewayFactory {
    public IBillPaymentGateway createBillGateway(String billType) throws CustomException;
}

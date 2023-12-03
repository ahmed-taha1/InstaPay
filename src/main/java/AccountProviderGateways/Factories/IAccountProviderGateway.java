package AccountProviderGateways.Factories;

import Exceptions.CustomException;

import java.util.Map;

interface IAccountProviderGateway {
    public AccountProviderGateways.IAccountProviderGateway createPaymentGateway(Map<String, Object> attributes) throws CustomException;
}

package AccountProviderGateways.Factories;

import AccountProviderGateways.IAccountProviderGateway;
import Exceptions.CustomException;
import Users.Entities.IUser;

import java.util.Map;

public interface IAccountProviderGatewayFactory {
    public IAccountProviderGateway createAccountProviderGateway(String accountProvider) throws CustomException;
}

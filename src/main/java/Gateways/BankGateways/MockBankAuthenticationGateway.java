package Gateways.BankGateways;

import Authentication.Exceptions.InvalidCredentials;
import Authentication.Exceptions.UnAuthenticated;
import Gateways.IAuthenticationGateway;

public class MockBankAuthenticationGateway implements IAuthenticationGateway {
    @Override
    public void authenticate() throws UnAuthenticated, InvalidCredentials {

    }
}

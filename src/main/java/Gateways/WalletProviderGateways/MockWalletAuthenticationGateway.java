package Gateways.WalletProviderGateways;

import Authentication.Exceptions.InvalidCredentials;
import Authentication.Exceptions.UnAuthenticated;
import Gateways.IAuthenticationGateway;

public class MockWalletAuthenticationGateway implements IAuthenticationGateway {
    String phoneNumber ;
    @Override
    public void authenticate() throws UnAuthenticated, InvalidCredentials {

    }
    @Override
    public void verifyCode(String code) {

    }
}

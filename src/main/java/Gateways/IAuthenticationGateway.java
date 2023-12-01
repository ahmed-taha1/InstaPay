package Gateways;

import Authentication.Exceptions.InvalidCredentials;
import Authentication.Exceptions.UnAuthenticated;

public interface IAuthenticationGateway {
    public void authenticate()throws UnAuthenticated, InvalidCredentials;
}

package Gateways;

import Authentication.Exceptions.InvalidCredentials;
import Authentication.Exceptions.UnAuthenticated;
import Authentication.Exceptions.UserNotFound;

public interface IAuthenticationGateway {
    public void authenticate()throws UnAuthenticated, InvalidCredentials;
    public void verifyCode(String code) throws UserNotFound, UnAuthenticated;
}
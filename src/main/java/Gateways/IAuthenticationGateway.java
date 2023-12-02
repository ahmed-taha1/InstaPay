package Gateways;

import Exceptions.CustomException;

public interface IAuthenticationGateway {
    public void authenticate()throws CustomException;
}

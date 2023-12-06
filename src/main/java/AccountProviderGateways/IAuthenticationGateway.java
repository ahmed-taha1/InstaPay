package AccountProviderGateways;

import Exceptions.CustomException;
import Users.Entities.IUser;

public interface IAuthenticationGateway {
    public void authenticate(IUser user) throws CustomException;

    public void verifyCode(IUser user,String code) throws CustomException;
}

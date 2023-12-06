package AccountProviderGateways;

import Exceptions.CustomException;
import Users.Entities.IUser;

public interface IAccountProviderGateway {
    public void depositMoney(IUser user,double amount) throws CustomException;

    public void withdrawMoney(IUser user,double amount) throws CustomException;

    public double getBalance(IUser user) throws CustomException;
}

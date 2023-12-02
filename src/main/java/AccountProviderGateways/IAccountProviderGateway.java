package AccountProviderGateways;
import Exceptions.CustomException;
public interface IAccountProviderGateway {
    public void depositMoney(double amount) throws CustomException;
    public void withdrawMoney(double amount) throws CustomException;
    public double getBalance() throws CustomException;
}

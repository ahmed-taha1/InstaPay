package PaymentGateways;
import Exceptions.CustomException;
public interface IAuthenticationGateway {
    public void authenticate()throws CustomException;
    public void verifyCode(String code) throws CustomException;
}

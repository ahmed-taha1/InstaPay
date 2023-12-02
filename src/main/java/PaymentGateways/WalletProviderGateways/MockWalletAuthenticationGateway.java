package PaymentGateways.WalletProviderGateways;
import Exceptions.CustomException;
import OTPGenerator.RandomGenerator;
import PaymentGateways.IAuthenticationGateway;
import StatusCodes.StatusCodes;
public class MockWalletAuthenticationGateway implements IAuthenticationGateway {
    private final String phoneNumber;
    public MockWalletAuthenticationGateway(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    @Override
    public void authenticate() throws CustomException {
        if(!MockWalletDB.isRegisteredUser(phoneNumber)){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Invalid Credentials");
        }
        sendVerificationCode();
    }
    @Override
    public void verifyCode(String code) throws CustomException{
        String storedCode =  MockWalletDB.getUserVerificationCode(phoneNumber);
        if(!storedCode.equals(code)){
            throw new CustomException(StatusCodes.UNAUTHENTICATED,"OTP doesn't match last sent code");
        }
    }
    private void sendVerificationCode(){
        String verificationCOde = RandomGenerator.generateOTPCode();
        System.out.println(verificationCOde);
        MockWalletDB.storeVerificationCode(this.phoneNumber, verificationCOde);
    }
}

package Gateways.WalletProviderGateways.MockWalletGateway;
import Authentication.Exceptions.InvalidCredentials;
import Authentication.Exceptions.UnAuthenticated;
import Authentication.Exceptions.UserNotFound;
import Gateways.IAuthenticationGateway;
import OTPGenerator.RandomGenerator;

public class MockWalletAuthenticationGateway implements IAuthenticationGateway {
    private final String phoneNumber;
    public MockWalletAuthenticationGateway(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    @Override
    public void authenticate() throws UnAuthenticated, InvalidCredentials {
        if(!MockWalletDB.isRegisteredUser(phoneNumber)){
            throw new UnAuthenticated("Invalid Credentials");
        }
        sendVerificationCode();
    }
    @Override
    public void verifyCode(String code) throws UserNotFound, UnAuthenticated {
        String storedCode =  MockWalletDB.getUserVerificationCode(phoneNumber);
        if(!storedCode.equals(code)){
            throw new UnAuthenticated("OTP doesn't match last sent code");
        }
    }
    private void sendVerificationCode(){
        String verificationCOde = RandomGenerator.generateOTPCode();
        System.out.println(verificationCOde);
        MockWalletDB.storeVerificationCode(this.phoneNumber, verificationCOde);
    }
}

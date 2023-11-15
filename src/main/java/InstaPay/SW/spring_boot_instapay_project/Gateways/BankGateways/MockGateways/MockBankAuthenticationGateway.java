package InstaPay.SW.spring_boot_instapay_project.Gateways.BankGateways.MockGateways;

import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.InvalidCredentials;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthenticated;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IAuthenticationGateway;
import InstaPay.SW.spring_boot_instapay_project.OTPGenerator.RandomGenerator;
public class MockBankAuthenticationGateway implements IAuthenticationGateway {
    private final String bankAccount;
    private final String phoneNumber;
    public MockBankAuthenticationGateway(String bankAccount, String phoneNumber){
        this.bankAccount = bankAccount;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public void authenticate() throws UnAuthenticated, InvalidCredentials {
        if(!MockBankDB.isRegisteredUser(bankAccount,phoneNumber)){
            throw new UnAuthenticated("Invalid Credentials");
        }
        sendVerificationCode();
    }
    @Override
    public void verifyCode(String code) throws UserNotFound, UnAuthenticated {
        String storedCode =  MockBankDB.getUserVerificationCode(bankAccount);
        if(!storedCode.equals(code)){
            throw new UnAuthenticated("OTP doesn't match last sent code");
        }
    }
    private void sendVerificationCode(){
        String verificationCOde = RandomGenerator.generateOTPCode();
        System.out.println(verificationCOde);
        MockBankDB.storeVerificationCode(this.bankAccount, verificationCOde);
    }
}

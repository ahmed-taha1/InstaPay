package Gateways.BankGateways.MockGateways;

import Authentication.Exceptions.InvalidCredentials;
import Authentication.Exceptions.UnAuthenticated;
import Authentication.Exceptions.UserNotFound;
import Gateways.IAuthenticationGateway;
import OTPGenerator.RandomGenerator;

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

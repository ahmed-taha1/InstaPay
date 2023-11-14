package Gateways.BankGateways.MockGateways;

import Authentication.Exceptions.InvalidCredentials;
import Authentication.Exceptions.UnAuthenticated;
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
    public void verifyCode(String code) {
    }
    private void sendVerificationCode(){
        MockBankDB.storeVerificationCode(this.bankAccount, RandomGenerator.generateOTPCode());
    }
}

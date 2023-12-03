package AccountProviderGateways.BankGateways;

import Exceptions.CustomException;
import AccountProviderGateways.IAuthenticationGateway;
import OTPGenerator.RandomGenerator;
import StatusCodes.StatusCodes;

public class MockBankAuthenticationGateway implements IAuthenticationGateway {
    private final String bankAccount;
    private final String phoneNumber;

    public MockBankAuthenticationGateway(String bankAccount, String phoneNumber) {
        this.bankAccount = bankAccount;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void authenticate() throws CustomException {
        if (!MockBankDB.isRegisteredUser(bankAccount, phoneNumber)) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Invalid Credentials");
        }
        sendVerificationCode();
    }

    @Override
    public void verifyCode(String code) throws CustomException {
        String storedCode = MockBankDB.getUserVerificationCode(bankAccount);
        if (!storedCode.equals(code)) {
            throw new CustomException(StatusCodes.UNAUTHENTICATED, "OTP doesn't match last sent code");
        }
    }

    private void sendVerificationCode() {
        String verificationCOde = RandomGenerator.generateOTPCode();
        System.out.println(verificationCOde);
        MockBankDB.storeVerificationCode(this.bankAccount, verificationCOde);
    }
}
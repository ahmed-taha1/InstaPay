package AccountProviderGateways.BankGateways;

import Exceptions.CustomException;
import AccountProviderGateways.IAuthenticationGateway;
import OTPGenerator.RandomGenerator;
import StatusCodes.StatusCodes;
import Users.Entities.AccountType;
import Users.Entities.BankUser;
import Users.Entities.IUser;

public class MockBankAuthenticationGateway implements IAuthenticationGateway {

    @Override
    public void authenticate(IUser user) throws CustomException {
        if(user.getInstaPayAccount().getAccountType() != AccountType.bankAccount){
            throw new CustomException(StatusCodes.UNAUTHORIZED,"User Is not a bankUser");
        }
        BankUser bankUser = (BankUser) user;
        if (!MockBankDB.isRegisteredUser(bankUser.getBankAccount(), bankUser.getInstaPayAccount().getPhoneNumber())) {
            throw new CustomException(StatusCodes.NOT_FOUND, "Invalid Credentials");
        }
        sendVerificationCode(user);
    }

    @Override
    public void verifyCode(IUser user,String code) throws CustomException {
        if(user.getInstaPayAccount().getAccountType() != AccountType.bankAccount){
            throw new CustomException(StatusCodes.UNAUTHORIZED,"User Is not a bankUser");
        }
        BankUser bankUser = (BankUser) user;
        String storedCode = MockBankDB.getUserVerificationCode(bankUser.getBankAccount());
        if (!storedCode.equals(code)) {
            throw new CustomException(StatusCodes.UNAUTHENTICATED, "OTP doesn't match last sent code");
        }
    }

    private void sendVerificationCode(IUser user) throws CustomException {
        if(user.getInstaPayAccount().getAccountType() != AccountType.bankAccount){
            throw new CustomException(StatusCodes.UNAUTHORIZED,"User Is not a bankUser");
        }
        BankUser bankUser = (BankUser) user;
        String verificationCOde = RandomGenerator.generateOTPCode();
        System.out.println(verificationCOde);
        MockBankDB.storeVerificationCode(bankUser.getBankAccount(), verificationCOde);
    }
}
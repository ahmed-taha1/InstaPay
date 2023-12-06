package AccountProviderGateways.WalletProviderGateways;

import Exceptions.CustomException;
import OTPGenerator.RandomGenerator;
import AccountProviderGateways.IAuthenticationGateway;
import StatusCodes.StatusCodes;
import Users.Entities.IUser;

public class MockWalletAuthenticationGateway implements IAuthenticationGateway {

    @Override
    public void authenticate(IUser user) throws CustomException {
        if (!MockWalletDB.isRegisteredUser(user.getInstaPayAccount().getPhoneNumber())) {
            throw new CustomException(StatusCodes.BAD_REQUEST, "Invalid Credentials");
        }
        sendVerificationCode(user);
    }
    @Override
    public void verifyCode(IUser user,String code) throws CustomException {
        String storedCode = MockWalletDB.getUserVerificationCode(user.getInstaPayAccount().getPhoneNumber());
        if (!storedCode.equals(code)) {
            throw new CustomException(StatusCodes.UNAUTHENTICATED, "OTP doesn't match last sent code");
        }
    }

    private void sendVerificationCode(IUser user) {
        String verificationCOde = RandomGenerator.generateOTPCode();
        System.out.println(verificationCOde);
        MockWalletDB.storeVerificationCode(user.getInstaPayAccount().getPhoneNumber(), verificationCOde);
    }
}

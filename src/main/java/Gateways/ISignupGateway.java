package Gateways;

public interface ISignupGateway {
    public void sendVerificationCode();
    public boolean verifyCode(String code);
}

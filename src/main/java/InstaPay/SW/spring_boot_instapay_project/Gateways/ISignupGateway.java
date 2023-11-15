package InstaPay.SW.spring_boot_instapay_project.Gateways;

public interface ISignupGateway {
    public void sendVerificationCode();
    public boolean verifyCode(String code);
}

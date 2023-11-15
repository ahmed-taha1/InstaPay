package InstaPay.SW.spring_boot_instapay_project.Gateways;


import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.InvalidCredentials;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthenticated;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;

public interface IAuthenticationGateway {
    public void authenticate()throws UnAuthenticated, InvalidCredentials;
    public void verifyCode(String code) throws UserNotFound, UnAuthenticated;
}
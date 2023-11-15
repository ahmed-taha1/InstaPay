package InstaPay.SW.spring_boot_instapay_project.Gateways;

import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.InvalidCredentials;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthenticated;

public interface IAuthenticationGateway {
    public void authenticate()throws UnAuthenticated, InvalidCredentials;
}

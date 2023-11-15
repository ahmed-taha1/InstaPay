package InstaPay.SW.spring_boot_instapay_project.Gateways.BankGateways;

import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.InvalidCredentials;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthenticated;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IAuthenticationGateway;

public class MockBankAuthenticationGateway implements IAuthenticationGateway {
    @Override
    public void authenticate() throws UnAuthenticated, InvalidCredentials {

    }
}

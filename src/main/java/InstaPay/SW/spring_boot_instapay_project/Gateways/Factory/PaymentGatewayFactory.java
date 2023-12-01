package InstaPay.SW.spring_boot_instapay_project.Gateways.Factory;

import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.InvalidCredentials;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Gateways.BankGateways.MockGateways.MockBankPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Gateways.WalletProviderGateways.MockWalletGateway.MockWalletPayementGateway;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.ITransaction;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.BankUser;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.User;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.WalletUser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PaymentGatewayFactory {
    private static PaymentGatewayFactory paymentGatewayFactoryInstance;
    private final Map<String, Function<Map<String, Object>, IPaymentGateway>> gatewayMapping;
    private PaymentGatewayFactory(){
        gatewayMapping = new HashMap<>();
        gatewayMapping.put("bank", this::createMockBankGateway);
        gatewayMapping.put("wallet", this::createMockWalletGateway);
    }
    public static PaymentGatewayFactory getInstance(){
        if(paymentGatewayFactoryInstance == null){
            paymentGatewayFactoryInstance = new PaymentGatewayFactory();
        }
        return paymentGatewayFactoryInstance;
    }
    public IPaymentGateway createGateway(String userType, Map<String, Object> map){
        Function<Map<String, Object>, IPaymentGateway> gatewayCreator = gatewayMapping.get(userType);
        if (gatewayCreator != null) {
            return  gatewayCreator.apply(map);
        }
        return null;
    }
    private IPaymentGateway createMockBankGateway(Map<String, Object> attr){
        if(attr.get("user") == null){
            return null;
        }
        BankUser user = (BankUser) attr.get("user");
        return new MockBankPaymentGateway(user.getAccountNumber());
    }
    private IPaymentGateway createMockWalletGateway(Map<String, Object> attr){
        if(attr.get("user") == null){
            return null;
        }
        WalletUser user = (WalletUser) attr.get("user");
        return new MockWalletPayementGateway(user.getPhoneNumber());
    }
}

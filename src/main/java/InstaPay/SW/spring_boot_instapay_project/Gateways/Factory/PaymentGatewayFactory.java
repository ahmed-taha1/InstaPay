package InstaPay.SW.spring_boot_instapay_project.Gateways.Factory;

import InstaPay.SW.spring_boot_instapay_project.Gateways.BankGateways.MockGateways.MockBankPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Gateways.WalletProviderGateways.MockWalletGateway.MockWalletPayementGateway;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.ITransaction;

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
    public ITransaction createGateway(String gatewayType, Map<String, Object> map){
        Function<Map<String, Object>, IPaymentGateway> gatewayCreator = gatewayMapping.get(gatewayType);
        if (gatewayCreator != null) {
            return (ITransaction) gatewayCreator.apply(map);
        }
        return null;
    }
    private IPaymentGateway createMockBankGateway(Map<String, Object> attr){
        return new MockBankPaymentGateway((String)attr.get("bankAccount"));
    }
    private IPaymentGateway createMockWalletGateway(Map<String, Object> attr){
        return new MockWalletPayementGateway((String)attr.get("phoneNumber"));
    }
}

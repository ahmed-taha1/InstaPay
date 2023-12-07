package App;

import AccountProviderGateways.Factories.AccountProviderGatewayGatewayFactory;
import AccountProviderGateways.Factories.IAccountProviderGatewayFactory;
import BillPaymentGateways.Factories.BillPaymentGatewayFactory;
import BillPaymentGateways.Factories.IBillPaymentGatewayFactory;
import Transactions.DataAccess.ITransactionsDataAccess;
import Transactions.DataAccess.InMemoryTransactions;
import Transactions.Factories.TransferFactory;
import Transactions.Services.TransferMoneyServices.TransferAuthorizerService;
import Users.DataAccess.IUserDataAccess;
import Users.DataAccess.InMemoryUsers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	public static final IUserDataAccess userDataAccess = new InMemoryUsers();
	public static final ITransactionsDataAccess transactionsDataAccess = InMemoryTransactions.getInstance();
	public static final TransferFactory transferFactory = TransferFactory.getInstance();
	public static final IAccountProviderGatewayFactory accountProviderGatewayFactory = AccountProviderGatewayGatewayFactory.getInstance();
	public static final TransferAuthorizerService transferAuthorizerService = new TransferAuthorizerService();
	public static final IBillPaymentGatewayFactory billPaymentGatewayFactory = BillPaymentGatewayFactory.getInstance();
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

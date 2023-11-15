package InstaPay.SW.spring_boot_instapay_project;

import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthenticated;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthorized;
import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UserNotFound;
import InstaPay.SW.spring_boot_instapay_project.Authentication.TransferAuthorizer;
import InstaPay.SW.spring_boot_instapay_project.Bills.Entities.BillInfo;
import InstaPay.SW.spring_boot_instapay_project.Bills.Entities.BillsTypes;
import InstaPay.SW.spring_boot_instapay_project.Bills.Factories.BillFactory;
import InstaPay.SW.spring_boot_instapay_project.Gateways.BankGateways.MockGateways.MockBankPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Gateways.Factory.PaymentGatewayFactory;
import InstaPay.SW.spring_boot_instapay_project.Gateways.IPaymentGateway;
import InstaPay.SW.spring_boot_instapay_project.Gateways.WalletProviderGateways.MockWalletGateway.MockWalletPayementGateway;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.ITransaction;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Entities.TransferMoney;
import InstaPay.SW.spring_boot_instapay_project.Transactions.Exceptions.InvalidBalance;
import InstaPay.SW.spring_boot_instapay_project.Users.DataAccess.IUserDataAccess;
import InstaPay.SW.spring_boot_instapay_project.Users.DataAccess.InMemoryUsers;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.BankUser;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.User;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.WalletUser;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class SpringBootInstapayProjectApplication {
	private static User activeUser = null;
	private static IUserDataAccess userDataAccess = InMemoryUsers.getInstance();
	public static void main(String[] args) {
		while (true){
			try {
				startWindow();
			}catch (Exception exception){
				System.out.println(exception.getMessage());
			}
		}
	}
	private static void startWindow() throws UserNotFound, UnAuthenticated, InvalidBalance, UnAuthorized {
		System.out.println("welcome");
		System.out.println("please choose option");
		System.out.println("1- Signup");
		System.out.println("2- Login");
		Stri choose = Integer.parseInt(new Scanner(System.in).nextLine());
		if(choose == 1){
			signup();
		}
		else{
			login();
		}
	}
	public static void login() throws UserNotFound, UnAuthenticated, InvalidBalance, UnAuthorized {
		System.out.println("Enter Your Phone Number");
		new Scanner(System.in).nextLine();
		String phoneNumber = new Scanner(System.in).nextLine();
		System.out.println("Enter your userName:");
		String userName = new Scanner(System.in).nextLine();
		System.out.println("Enter Password");
		String password = new Scanner(System.in).nextLine();
		User user = userDataAccess.getUserByUserName(userName);
		if(user == null){
			throw new UserNotFound("User doesn't Exist");
		}
		if(!user.getPassword().equals(password)){
			throw new UnAuthenticated("Invalid Credentials");
		}
		System.out.println("Logged in Successfully....");
		activeUser = user;
		transactionWindow();
	}
	public static void signup(){
		String userName = new Scanner(System.in).nextLine();
		System.out.println("Enter your userName: ");

		if(userDataAccess.getUserByUserName(userName) != null){
			System.out.println("user already exist");
			return;
		}
		System.out.println("Enter password: ");
		String password = new Scanner(System.in).nextLine();

		System.out.println("Enter you phone Number: ");
		String phoneNumber = new Scanner(System.in).nextLine();
		System.out.println("OTP sent");

		System.out.println("Bank user type(1) or wallet user type(2)? : ");

		int choose = new Scanner(System.in).nextInt();

		if(choose == 1){
			String bankAccountNumber = new Scanner(System.in).nextLine();
			userDataAccess.createUser(new BankUser(userName, password, phoneNumber, bankAccountNumber));
		}
		else if(choose == 2){
			System.out.println("please enter your wallet provider");
			String walletProvider = new Scanner(System.in).nextLine();
			userDataAccess.createUser(new WalletUser(userName, password, phoneNumber, walletProvider));
		}
	}
	public static void transactionWindow() throws UserNotFound, UnAuthorized, InvalidBalance {
		System.out.println("1-Transfer To BankAccount");
		System.out.println("2-Transfer To WalletUser");
		System.out.println("3-Transfer To InstaPayUser");
		System.out.println("4-Pay Bill");
		System.out.println("5-Inquire About Balance");
		int choice = new Scanner(System.in).nextInt();
		if(choice == 1){
			transferToBankAccount();
		} else if (choice == 2) {
			transferToWalletUser();
		}else if(choice == 3){
			transferToInstaPay();
		}else if(choice == 4){
			payBill();
		}else if(choice == 5){
			InquireAboutBalance();
		}
	}
	public static void transferToBankAccount() throws UserNotFound, UnAuthorized, InvalidBalance {
		System.out.println("Please Enter Receiver Phone Number:");
		String phoneNumber = new Scanner(System.in).nextLine();
		System.out.println("Please Enter The amount to transfer");
		Double amount = new Scanner(System.in).nextDouble();
		User receiver = userDataAccess.getUserByMobileNumber(phoneNumber);
		TransferAuthorizer authorizer = new TransferAuthorizer(activeUser.getUserType(),receiver.getUserType());
		Map<String,Object>senderAttributes = new HashMap<>();
		senderAttributes.put("user", activeUser);
		IPaymentGateway senderGateway = PaymentGatewayFactory.getInstance().createGateway(activeUser.getUserType(),senderAttributes);
		Map<String,Object>recieverAttribures = new HashMap<>();
		senderAttributes.put("user",receiver);
		IPaymentGateway recieverGateway = PaymentGatewayFactory.getInstance().createGateway(receiver.getUserType(),recieverAttribures);
		ITransaction transaction = new TransferMoney(senderGateway,recieverGateway,authorizer);
		transaction.executeTransaction(amount);
	}

	public static void transferToWalletUser()  throws UnAuthorized, InvalidBalance, UserNotFound{
		System.out.println("please enter receiver phone number");
		String receiverPhoneNumber = new Scanner(System.in).nextLine();

		System.out.println("please enter the amount");
		double amount = new Scanner(System.in).nextDouble();

		User receiver = userDataAccess.getUserByMobileNumber(receiverPhoneNumber);

		TransferAuthorizer transferAuthorizer = new TransferAuthorizer(activeUser.getUserType(), receiver.getUserType());

		PaymentGatewayFactory paymentGatewayFactory = PaymentGatewayFactory.getInstance();

		Map<String, Object> user1 = new HashMap<>();
		user1.put("user" ,activeUser);

		Map<String, Object> user2 = new HashMap<>();
		user2.put("user" ,receiver);
		IPaymentGateway senderPaymentGateway= paymentGatewayFactory.createGateway(activeUser.getUserType(), user1);
		IPaymentGateway receiverPaymentGateway = paymentGatewayFactory.createGateway(activeUser.getUserType(), user2);

		TransferMoney transaction = new TransferMoney(senderPaymentGateway, receiverPaymentGateway, transferAuthorizer);
		transaction.executeTransaction(amount);
	}

	public static void transferToInstaPay() throws UnAuthorized, InvalidBalance, UserNotFound{
		System.out.println("Please Enter Receiver UserName:");
		String userName = new Scanner(System.in).nextLine();
		System.out.println("Please Enter The amount to transfer");
		Double amount = new Scanner(System.in).nextDouble();
		User receiver = userDataAccess.getUserByUserName(userName);
		TransferAuthorizer authorizer = new TransferAuthorizer(activeUser.getUserType(),receiver.getUserType());
		Map<String, Object>senderAttributes = new HashMap<>();
		senderAttributes.put("user",activeUser);
		IPaymentGateway senderGateway = PaymentGatewayFactory.getInstance().createGateway(activeUser.getUserType(),senderAttributes);
		Map<String,Object>recieverAttribures = new HashMap<>();
		senderAttributes.put("user",receiver);
		IPaymentGateway recieverGateway = PaymentGatewayFactory.getInstance().createGateway(receiver.getUserType(),recieverAttribures);
		ITransaction transaction = new TransferMoney(senderGateway,recieverGateway,authorizer);
	}
	public static void payBill(){
		System.out.println("please choose providor");
		ArrayList<String> billsTypes = BillsTypes.getBillsTypes();
		for(int i = 0; i < billsTypes.size(); i++){
			System.out.println((i + 1) + "- " + billsTypes.get(i));
		}
		System.out.print(">> ");
		int choose = new Scanner(System.in).nextInt();
		BillFactory billFactory = BillFactory.getBillFactoryInstance();
		BillInfo bill = billFactory.createBillStrategy(choose);
		bill.pay();
	}
	public static void InquireAboutBalance(){
		Map<String, Object> user1 = new HashMap<>();
		if(activeUser.getUserType() == "bank"){
			BankUser user = (BankUser) activeUser;
			MockBankPaymentGateway mockBankPaymentGateway = new MockBankPaymentGateway(user.getAccountNumber());
			double amount = 0;
			try {
				amount = mockBankPaymentGateway.getBalance();
			} catch (UserNotFound e) {
				throw new RuntimeException(e);
			}
			System.out.println("your balance is" + amount);
		}
		else{
			MockWalletPayementGateway mockWalletPayementGatey = new MockWalletPayementGateway(activeUser.getPhoneNumber());
			try {
				double amount = mockWalletPayementGatey.getBalance();
				System.out.println("your balance is" + amount);
			} catch (UserNotFound e) {
				throw new RuntimeException(e);
			}
		}
	}
}
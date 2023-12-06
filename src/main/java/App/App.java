package App;
import Transactions.Factories.BillPaymentFactory;
import Transactions.Factories.TransferFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App {
	public TransferFactory transferFactory = TransferFactory.getInstance();
	public BillPaymentFactory billPaymentFactory = BillPaymentFactory.getInstance();
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

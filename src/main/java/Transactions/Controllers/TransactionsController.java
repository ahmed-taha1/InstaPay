package Transactions.Controllers;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/")
public class TransactionsController {
    @GetMapping
    public String makeTransfer(){
       return "make transaction";
    }
    @PostMapping
    public String makeBillPayment(){
        return "bill paymnet";
    }
}

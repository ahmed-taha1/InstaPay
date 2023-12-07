package App;

import AccountProviderGateways.IAccountProviderGateway;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Transactions.Entities.TransactionType;
import Transactions.Services.ITransactionService;
import Users.Entities.IUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/")
public class TransactionsController {
    @PostMapping("/transfer")
    public ResponseEntity<Map<String, String>> makeTransfer(@RequestBody Map<String,String> body){
        System.out.println(body);
        Map<String, String> response = new HashMap<>();

        String transactionType = body.get("transactionType");
        ITransactionService transactionService = App.transferFactory.createTransferInstance(
                transactionType,
                body,
                App.transferAuthorizerService,
                App.accountProviderGatewayFactory,
                App.userDataAccess,
                App.transactionsDataAccess
        );
        if(transactionService == null){
            response.put("status","fail");
            response.put("message","transaction type not supported");
            return ResponseEntity.status(StatusCodes.BAD_REQUEST).body(response);
        }
        String amount = body.get("amount");
        if(amount == null){
            response.put("status", "fail");
            response.put("message", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            transactionService.executeTransaction(Double.parseDouble(amount));
            response.put("status", "success");
            response.put("message", "Transaction Done....");
            return ResponseEntity.status(StatusCodes.SUCCESS).body(response);
        }catch (CustomException exception){
            response.put("status", "fail");
            response.put("message", exception.getMessage());
            int statusCode = exception.getStatusCode();
            return ResponseEntity.status(statusCode).body(response);
        }
    }
    @PostMapping("/billPayment")
    public String makeBillPayment(){
        return "bill payment";
    }

    @GetMapping("/balance")
    public ResponseEntity<Map<String, String>> getBalance(@RequestBody Map<String, String> body) {
        Map<String, String> response = new HashMap<>();
        String userName = body.get("userName");
        IUser user = App.userDataAccess.getUserByUserName(userName);
        if (user == null) {
            response.put("status", "fail");
            response.put("message", "User not found");
            return ResponseEntity.status(StatusCodes.NOT_FOUND).body(response);
        }
        try {
            IAccountProviderGateway gateway = App.accountProviderGatewayFactory.createAccountProviderGateway(user.getInstaPayAccount().getAccountProvider());
            double balance = gateway.getBalance(user);
            response.put("status", "success");
            response.put("balance", Double.toString(balance));
            return ResponseEntity.status(StatusCodes.SUCCESS).body(response);

        } catch (CustomException exception) {
            response.put("status", "fail");
            response.put("message", exception.getMessage());
            int statusCode = exception.getStatusCode();
            return ResponseEntity.status(statusCode).body(response);
        }
    }
}

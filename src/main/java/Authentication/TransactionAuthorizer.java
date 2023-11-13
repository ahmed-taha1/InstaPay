package Authentication;

import Authentication.Exceptions.UnAuthorized;

import java.util.List;
import java.util.Map;

public class TransactionAuthorizer implements IAuthorizer {
    private static Map<String, List<String>>validActionsMapper;
    private static void test(){
//        validActionsMapper.put("");
    }
    public TransactionAuthorizer(String transactionType,String senderType,String receiverType){

    }
    @Override
    public void validateAction(){
    }
}

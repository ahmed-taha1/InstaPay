package Authentication;
import Authentication.Exceptions.UnAuthorized;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionAuthorizer{
    private final String senderType;
    private final String receiverType;
    private static Map<String, List<String>> validActionsMapper;
    private static void test(){
        validActionsMapper.put("bank",getAllowedBankActions());
        validActionsMapper.put("wallet",getAllowedWalletActions());
    }
    public static List<String>getAllowedBankActions(){
        ArrayList<String> allowedBankActionsList = new ArrayList<>();
        allowedBankActionsList.add("all");
        return allowedBankActionsList;
    }
    public static List<String>getAllowedWalletActions(){
        ArrayList<String>allowedWalletActionsList = new ArrayList<>();
        allowedWalletActionsList.add("wallet");
        return allowedWalletActionsList;
    }
    public TransactionAuthorizer(String senderType,String receiverType){
        this.senderType = senderType;
        this.receiverType = receiverType;
    }
    public void isValidateAction()throws UnAuthorized{
        if(validActionsMapper.get(this.senderType) == null){
            throw new UnAuthorized("Invalid action");
        }
        if(validActionsMapper.get(this.senderType.toLowerCase()).contains("all")){
            return;
        }
        if(!validActionsMapper.get(this.senderType.toLowerCase()).contains(receiverType.toLowerCase())){
            throw new UnAuthorized(senderType + " User cannot send money to " + receiverType);
        }
    }
}
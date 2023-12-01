package InstaPay.SW.spring_boot_instapay_project.Authentication;


import InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions.UnAuthorized;
import InstaPay.SW.spring_boot_instapay_project.Users.Entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferAuthorizer {
    private final Map<String, List<String>> validActionsMapper;
    private final String senderType;
    private final String receiverType;
    private void addActions(){
        validActionsMapper.put("bank",getAllowedBankActions());
        validActionsMapper.put("wallet",getAllowedWalletActions());
    }

    private static List<String>getAllowedBankActions(){
        ArrayList<String> allowedBankActionsList = new ArrayList<>();
        allowedBankActionsList.add("all");
        return allowedBankActionsList;
    }

    private static List<String>getAllowedWalletActions(){
        ArrayList<String>allowedWalletActionsList = new ArrayList<>();
        allowedWalletActionsList.add("wallet");
        return allowedWalletActionsList;
    }

    public TransferAuthorizer(String senderType, String receiverType){
        this.validActionsMapper = new HashMap<>();
        this.addActions();
        this.senderType = senderType;
        this.receiverType = receiverType;
    }

    public void validateAction()throws UnAuthorized {
        if(validActionsMapper.get(this.senderType.toLowerCase()) == null){
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
package Authentication;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Users.Entities.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferAuthorizer {
    private final Map<String, List<String>> validActionsMapper;
    private final UserProfile sender;
    private final UserProfile receiver;
    private void addActions(){
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
    public TransferAuthorizer(UserProfile sender, UserProfile receiver){
        this.validActionsMapper = new HashMap<>();
        this.addActions();
        this.sender = sender;
        this.receiver = receiver;
    }
    public void validateAction()throws CustomException {
        if(validActionsMapper.get(this.sender.getUserType().toLowerCase()) == null){
            throw new CustomException(StatusCodes.UNAUTHORIZED,"UnAuthorized action");
        }
        if(validActionsMapper.get(this.sender.getUserType().toLowerCase()).contains("all")){
            return;
        }
        if(!validActionsMapper.get(this.sender.getUserType().toLowerCase()).contains(receiver.getUserType().toLowerCase())){
            throw new CustomException(StatusCodes.FORBIDDEN,sender + " User cannot send money to " + receiver);
        }
    }
}
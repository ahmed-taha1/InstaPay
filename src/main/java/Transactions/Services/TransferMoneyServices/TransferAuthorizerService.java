package Transactions.Services.TransferMoneyServices;

import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Users.Entities.AccountType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferAuthorizerService {
    private final Map<String, List<String>> validActionsMapper;
    private void addActions() {
        validActionsMapper.put(AccountType.BANK_USER.toString(), getAllowedBankTransfers());
        validActionsMapper.put(AccountType.WALLET_USER.toString(), getAllowedWalletTransfers());
    }

    private List<String> getAllowedBankTransfers() {
        ArrayList<String> allowedBankActionsList = new ArrayList<>();
        allowedBankActionsList.add("all");
        return allowedBankActionsList;
    }

    private List<String> getAllowedWalletTransfers() {
        ArrayList<String> allowedWalletActionsList = new ArrayList<>();
        allowedWalletActionsList.add("walletAccount");
        return allowedWalletActionsList;
    }

    public TransferAuthorizerService(String senderType, String receiverType) {
        this.validActionsMapper = new HashMap<>();
        this.addActions();
    }

    public void validateAction(String senderType,String receiverType) throws CustomException {
        if (validActionsMapper.get(senderType.toLowerCase()) == null) {
            throw new CustomException(StatusCodes.UNAUTHORIZED, "Invalid action");
        }
        if (validActionsMapper.get(senderType.toLowerCase()).contains("all")) {
            return;
        }
        if (!validActionsMapper.get(senderType.toLowerCase()).contains(receiverType.toLowerCase())) {
            throw new CustomException(StatusCodes.UNAUTHORIZED, senderType + " User cannot send money to " + receiverType);
        }
    }
}
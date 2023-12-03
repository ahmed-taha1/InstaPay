package Authentication;

import Exceptions.CustomException;
import StatusCodes.StatusCodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferAuthorizer {
    private final Map<String, List<String>> validActionsMapper;
    private final String senderType;
    private final String receiverType;

    private void addActions() {
        validActionsMapper.put("bank", getAllowedBankTransfers());
        validActionsMapper.put("wallet", getAllowedWalletTransfers());
    }

    private static List<String> getAllowedBankTransfers() {
        ArrayList<String> allowedBankActionsList = new ArrayList<>();
        allowedBankActionsList.add("all");
        return allowedBankActionsList;
    }

    private static List<String> getAllowedWalletTransfers() {
        ArrayList<String> allowedWalletActionsList = new ArrayList<>();
        allowedWalletActionsList.add("wallet");
        return allowedWalletActionsList;
    }

    public TransferAuthorizer(String senderType, String receiverType) {
        this.validActionsMapper = new HashMap<>();
        this.addActions();
        this.senderType = senderType;
        this.receiverType = receiverType;
    }

    public void validateAction() throws CustomException {
        if (validActionsMapper.get(this.senderType.toLowerCase()) == null) {
            throw new CustomException(StatusCodes.UNAUTHORIZED, "Invalid action");
        }
        if (validActionsMapper.get(this.senderType.toLowerCase()).contains("all")) {
            return;
        }
        if (!validActionsMapper.get(this.senderType.toLowerCase()).contains(receiverType.toLowerCase())) {
            throw new CustomException(StatusCodes.UNAUTHORIZED, senderType + " User cannot send money to " + receiverType);
        }
    }
}
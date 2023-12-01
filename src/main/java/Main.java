import Bills.Entities.BillsTypes;
import Transactions.Entities.TransactionType;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> bills = BillsTypes.getBillsTypes();
        System.out.println(bills);
        System.out.println(TransactionType.TRANSFER_TO_BANK.getIndex());
    }
}
package InstaPay.SW.spring_boot_instapay_project.Bills.Factories;



import InstaPay.SW.spring_boot_instapay_project.Bills.Entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillFactory {
    private static BillFactory instance = null;
    private final Map<Integer, BillInfo> billMapping;
    BillFactory(){
        billMapping = new HashMap<>();
        billMapping.put(BillsTypes.ELECTRICITY_BILL.getIndex(), new ElectricityBill());
        billMapping.put(BillsTypes.GAS_BILL.getIndex(), new GasBill());
        billMapping.put(BillsTypes.WATER_BILL.getIndex(), new WaterBill());
    }
    static public BillFactory getBillFactoryInstance(){
        if(instance == null)
                instance = new BillFactory();
        return instance;
    }
    public BillInfo createBillStrategy(int billNumber){
        return billMapping.get(billNumber);
    }
    public ArrayList<String> getAvailableBills(){
        return BillsTypes.getBillsTypes();
    }
}
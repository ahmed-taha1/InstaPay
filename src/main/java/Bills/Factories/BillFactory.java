package Bills.Factories;

import Bills.Entities.BillInfo;
import Bills.Entities.ElectricityBill;
import Bills.Entities.GasBill;
import Bills.Entities.WaterBill;

import java.util.HashMap;
import java.util.Map;

public class BillFactory {
    public static String ELECTRICITY = "electricity bill";
    public static String WATER = "water bill";
    public static String GAS = "gas bill";
    private static BillFactory instance = null;
    private final Map<String, BillInfo> billMapping = new HashMap<>();
    BillFactory(){
        billMapping.put(ELECTRICITY, new ElectricityBill());
        billMapping.put(WATER, new WaterBill());
        billMapping.put(GAS, new GasBill());
    }
    static public BillFactory getBillFactoryInstance(){
        if(instance == null)
                instance = new BillFactory();
        return instance;
    }
    public BillInfo createBillStrategy(String billName){
        return billMapping.get(billName);
    }

    public String[] getAvailableBills(){
        return billMapping.keySet().toArray(new String[0]);
    }
}
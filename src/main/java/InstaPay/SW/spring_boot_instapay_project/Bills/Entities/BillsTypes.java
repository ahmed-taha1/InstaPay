package InstaPay.SW.spring_boot_instapay_project.Bills.Entities;

import java.util.ArrayList;

public enum BillsTypes {
    ELECTRICITY_BILL(1, "electricity bill"),
    GAS_BILL(2, "gas bill"),
    WATER_BILL(3, "water bill");
    private final int index;
    private final String typeName;
    BillsTypes(int index, String typeName){
        this.index = index;
        this.typeName = typeName;
    }
    public int getIndex() {
        return index;
    }

    public static ArrayList<String> getBillsTypes(){
        ArrayList<String> BillsTypes = new ArrayList<>();
        for(BillsTypes billType : BillsTypes.class.getEnumConstants()){
            BillsTypes.add(billType.typeName);
        }
        return BillsTypes;
    }
}

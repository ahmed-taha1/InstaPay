package InstaPay.SW.spring_boot_instapay_project.Bills.Entities;

public class ElectricityBill extends BillInfo{
    //    add it's private Data
    @Override
    public void pay() {
        System.out.println("Electricity bill paid");
    }
}

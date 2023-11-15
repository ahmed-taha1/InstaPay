package InstaPay.SW.spring_boot_instapay_project.Bills.Entities;

public abstract class BillInfo {
    public abstract double CalculateBillCost();
    private double cost;
    private int serialNumber;
}

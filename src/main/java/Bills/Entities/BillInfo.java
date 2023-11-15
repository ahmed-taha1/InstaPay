package Bills.Entities;

public abstract class BillInfo {
    public abstract double CalculateBillCost();
    private double cost;
    private int serialNumber;
}

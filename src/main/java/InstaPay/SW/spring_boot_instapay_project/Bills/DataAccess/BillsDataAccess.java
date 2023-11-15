package InstaPay.SW.spring_boot_instapay_project.Bills.DataAccess;


import InstaPay.SW.spring_boot_instapay_project.Bills.Entities.BillInfo;

public interface BillsDataAccess {
    BillInfo getBillBySerialNumber(int serialNumber);
    void createBill(BillInfo bill);
    void updateBill(BillInfo bill);
}

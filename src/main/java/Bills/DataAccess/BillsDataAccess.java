package Bills.DataAccess;

import Bills.Entities.BillInfo;

public interface BillsDataAccess {
    BillInfo getBillBySerialNumber(int serialNumber);
    void createBill(BillInfo bill);
    void updateBill(BillInfo bill);
}

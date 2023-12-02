package BillPaymentGateways.Entities;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
public class MockBillsPaymentGateway implements IBillsPaymentGateway {
    private final String userPhoneNumber;
    public MockBillsPaymentGateway(String userPhoneNumber){
        this.userPhoneNumber = userPhoneNumber;
    }
    @Override
    public void payBill(double amount) throws CustomException {
        double pillDuePrice = MockBillsDB.getDueBillPrice(userPhoneNumber);
        if(amount > pillDuePrice){
            throw new CustomException(StatusCodes.FORBIDDEN,"Cannot Pay with more than the required Bill amount");
        }
        MockBillsDB.updateDueBillPrice(userPhoneNumber,pillDuePrice - amount);
    }
}
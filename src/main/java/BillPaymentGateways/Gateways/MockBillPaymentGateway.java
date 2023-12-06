package BillPaymentGateways.Gateways;

import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import Users.Entities.IUser;

public class MockBillPaymentGateway implements IBillPaymentGateway {

    @Override
    public void payBill(IUser user,double amount) throws CustomException {
        double pillDuePrice = MockBillsDB.getDueBillPrice(user.getInstaPayAccount().getPhoneNumber());
        if (amount > pillDuePrice) {
            throw new CustomException(StatusCodes.FORBIDDEN, "Cannot Pay with more than the required Bill amount");
        }
        MockBillsDB.updateDueBillPrice(user.getInstaPayAccount().getPhoneNumber(), pillDuePrice - amount);
    }
}
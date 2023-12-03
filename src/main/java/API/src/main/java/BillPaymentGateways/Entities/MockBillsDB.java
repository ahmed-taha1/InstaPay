package BillPaymentGateways.Entities;
import Exceptions.CustomException;
import StatusCodes.StatusCodes;
import java.util.HashMap;
import java.util.Map;
class MockBillsUser {
    String phoneNumber;
    Double currentDueBillPrice;
    MockBillsUser(String phoneNumber, Double currentDueBillPrice){
        this.phoneNumber = phoneNumber;
        this.currentDueBillPrice = currentDueBillPrice;
    }
}
public class MockBillsDB {
    private static Map<String, MockBillsUser> mockBillsDB;
    static {
        seedMockDB();
    }
    private static void seedMockDB(){
        mockBillsDB = new HashMap<>();
        mockBillsDB.put("01111155566",new MockBillsUser("01111155566",1000.0));
        mockBillsDB.put("01157077022",new MockBillsUser("01157077022",1000.0));
        mockBillsDB.put("01120293048",new MockBillsUser("01120293048",10000.0));
        mockBillsDB.put("01122222356",new MockBillsUser("01122222356",220.0));
        mockBillsDB.put("01023875910",new MockBillsUser("01023875910",100.0));
    }
    static boolean findUserByPhoneNumber(String phoneNumber){
        return mockBillsDB.get(phoneNumber) != null;
    }
    static Double getDueBillPrice(String phoneNumber) throws CustomException {
        if(!findUserByPhoneNumber(phoneNumber)){
            throw new CustomException(StatusCodes.NOT_FOUND,"Bank Account doesn't exist in MockBills ");
        }
        return mockBillsDB.get(phoneNumber).currentDueBillPrice;
    }
    static void updateDueBillPrice(String phoneNumber, Double newDueBillPrice) throws CustomException {
        if(!findUserByPhoneNumber(phoneNumber)){
            throw new CustomException(StatusCodes.NOT_FOUND,"PhoneNumber  doesn't exist in MockBills ");
        }
        if(newDueBillPrice < 0 ){
            throw new CustomException(StatusCodes.BAD_REQUEST,"Cannot pay more than the due bill price");
        }
        MockBillsUser user = mockBillsDB.get(phoneNumber);
        user.currentDueBillPrice = newDueBillPrice;
    }
}
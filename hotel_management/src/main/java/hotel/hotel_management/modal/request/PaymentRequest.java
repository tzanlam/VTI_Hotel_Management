package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.entity.Payment;
import lombok.Data;

import static hotel.hotel_management.methodSupport.Method.convertStringToEnum;

@Data
public class PaymentRequest {
    private int bookedId;
    private int accountId;
    private String amount;
    private String typePayment;

    public Payment addPay(){
        Payment payment = new Payment();
        payment.setTypePayment(convertStringToEnum(Payment.TypePayment.class, typePayment));
        if (payment.getTypePayment() == Payment.TypePayment.CASH){
            payment.setStatus(Payment.PaymentStatus.SUCCESS);
        }else {
            payment.setStatus(Payment.PaymentStatus.NOT_CONFIRM);
        }
        return payment;
    }
}

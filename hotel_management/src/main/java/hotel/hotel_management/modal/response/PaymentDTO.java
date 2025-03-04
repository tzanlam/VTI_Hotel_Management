package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Payment;
import lombok.Data;

@Data
public class PaymentDTO {
    private int id;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
    }
}

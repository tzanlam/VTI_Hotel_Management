package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Payment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class PaymentDTO {
    private int id;
    private List<BookedDTO> booked;
    private String amount;
    private String typePayment;
    private String status;
    private String date;
    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.booked = Objects.nonNull(payment.getBooked()) ? payment.getBooked()
                .stream().map(BookedDTO::new).collect(Collectors.toList()): new ArrayList<>();
        this.amount = String.valueOf(payment.getAmount());
        this.typePayment = String.valueOf(payment.getTypePayment());
        this.status = String.valueOf(payment.getStatus());
        this.date = String.valueOf(payment.getCreated());
    }
}

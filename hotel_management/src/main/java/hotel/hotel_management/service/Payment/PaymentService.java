package hotel.hotel_management.service.Payment;

import hotel.hotel_management.modal.request.PaymentRequest;
import hotel.hotel_management.modal.response.PaymentDTO;

import java.util.List;

public interface PaymentService {
    // get
    List<PaymentDTO> findAllByHotelId(int hotelId);
    List<PaymentDTO> findPaymentByAccountId(int accountId);
    PaymentDTO findPaymentById(int id);

    // post
    PaymentDTO createPayment(PaymentRequest request);

    // delete
    String deletePaymentById(int id);
}

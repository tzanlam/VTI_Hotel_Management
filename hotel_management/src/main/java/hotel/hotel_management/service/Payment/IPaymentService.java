package hotel.hotel_management.service.Payment;

import hotel.hotel_management.modal.entity.Account;
import hotel.hotel_management.modal.entity.Booked;
import hotel.hotel_management.modal.entity.Payment;
import hotel.hotel_management.modal.request.PaymentRequest;
import hotel.hotel_management.modal.response.PaymentDTO;
import hotel.hotel_management.repository.AccountRepository;
import hotel.hotel_management.repository.BookedRepository;
import hotel.hotel_management.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IPaymentService implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final BookedRepository bookedRepository;
    private final AccountRepository accountRepository;

    public IPaymentService(PaymentRepository paymentRepository, BookedRepository bookedRepository, AccountRepository accountRepository) {
        this.paymentRepository = paymentRepository;
        this.bookedRepository = bookedRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<PaymentDTO> findAllByHotelId(int hotelId) {
        return paymentRepository.findByHotelId(hotelId).stream().map(PaymentDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> findPaymentByAccountId(int accountId) {
        return paymentRepository.findByAccountId(accountId).stream().map(PaymentDTO::new).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO findPaymentById(int id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Payment not found")
        );
        return new PaymentDTO(payment);
    }

    @Override
    public PaymentDTO createPayment(PaymentRequest request) {
        Booked booked = bookedRepository.findById(request.getBookedId()).orElseThrow(
                () -> new IllegalArgumentException("Booked not found")
        );
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(
                () -> new IllegalArgumentException("Account not found")
        );
        Payment payment = request.addPay();
        payment.setAccount(account);
        payment.getBooked().add(booked);
        paymentRepository.save(payment);
        return new PaymentDTO(payment);
    }

    @Override
    public String deletePaymentById(int id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Payment not found")
        );
        paymentRepository.delete(payment);
        return "Deleted payment";
    }
}

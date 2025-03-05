package hotel.hotel_management.service.invoice;

import hotel.hotel_management.modal.entity.Account;
import hotel.hotel_management.modal.entity.Hotel;
import hotel.hotel_management.modal.entity.Invoice;
import hotel.hotel_management.modal.request.InvoiceRequest;
import hotel.hotel_management.modal.response.InvoiceDTO;
import hotel.hotel_management.repository.AccountRepository;
import hotel.hotel_management.repository.HotelRepository;
import hotel.hotel_management.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IInvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final AccountRepository accountRepository;
    private final HotelRepository hotelRepository;

    public IInvoiceServiceImpl(InvoiceRepository invoiceRepository, AccountRepository accountRepository, HotelRepository hotelRepository) {
        this.invoiceRepository = invoiceRepository;
        this.accountRepository = accountRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<InvoiceDTO> findInvoiceByHotelId(int hotelId) {
        return invoiceRepository.findByHotelId(hotelId).stream().map(InvoiceDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> findByAccountId(int accountId) {
        return invoiceRepository.findByAccountId(accountId).stream().map(InvoiceDTO::new).collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO findInvoiceById(int id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Invoice not found")
        );
        return new InvoiceDTO(invoice);
    }

    @Override
    public InvoiceDTO save(InvoiceRequest request) {
        Invoice invoice = request.addInvoice();
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(
                () -> new NullPointerException("Account not found")
        );
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(
                () -> new NullPointerException("Hotel not found")
        );
        invoice.setAccount(account);
        invoice.setHotel(hotel);
        invoiceRepository.save(invoice);
        return new InvoiceDTO(invoice);
    }

    @Override
    public InvoiceDTO setStatus(int id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Invoice not found")
        );
        invoice.setStatus(Invoice.StatusPaymentInvoice.PAID);
        invoiceRepository.save(invoice);
        return new InvoiceDTO(invoice);
    }

    @Override
    public String deleteInvoice(int id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Invoice not found")
        );
        if (invoice.getStatus() == Invoice.StatusPaymentInvoice.UNPAID) {
            invoiceRepository.delete(invoice);
        }else {
            throw new RuntimeException("Invoice released");
        }
        return "Deleted Invoice";
    }
}

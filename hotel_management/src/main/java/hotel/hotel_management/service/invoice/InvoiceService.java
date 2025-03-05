package hotel.hotel_management.service.invoice;

import hotel.hotel_management.modal.request.InvoiceRequest;
import hotel.hotel_management.modal.response.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    // get
    List<InvoiceDTO> findInvoiceByHotelId(int hotelId);
    List<InvoiceDTO> findByAccountId(int accountId);
    InvoiceDTO findInvoiceById(int id);

    // post
    InvoiceDTO save(InvoiceRequest request);
    InvoiceDTO setStatus(int id);

    // delete
    String deleteInvoice(int id);
}

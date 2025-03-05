package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Invoice;
import lombok.Data;

@Data
public class InvoiceDTO {
    private int id;
    private String name;
    private int price;
    private int accountId;
    private int hotelId;
    private String customerName;
    private String paymentMethod;
    private String status;

    public InvoiceDTO(Invoice invoice) {
        this.id = invoice.getId();
        this.name = invoice.getName();
        this.price = (int) invoice.getPrice();
        this.accountId = invoice.getAccount().getId();
        this.hotelId = invoice.getHotel().getId();
        this.customerName = invoice.getCustomerName();
        this.paymentMethod = String.valueOf(invoice.getPaymentMethod());
        this.status = String.valueOf(invoice.getStatus());
    }
}

package hotel.hotel_management.modal.request;

import hotel.hotel_management.modal.constant.PaymentMethod;
import hotel.hotel_management.modal.entity.Invoice;
import lombok.Data;

import static hotel.hotel_management.methodSupport.Method.convertStringToEnum;

@Data
public class InvoiceRequest {
    private String name;
    private String price;
    private int accountId;
    private int hotelId;
    private String customerName;
    private String paymentMethod;

    public Invoice addInvoice(){
        Invoice invoice = new Invoice();
        invoice.setName(name);
        invoice.setPrice(Double.parseDouble(price));
        invoice.setCustomerName(customerName);
        invoice.setPaymentMethod(convertStringToEnum(PaymentMethod.class, paymentMethod));
        return invoice;
    }
}

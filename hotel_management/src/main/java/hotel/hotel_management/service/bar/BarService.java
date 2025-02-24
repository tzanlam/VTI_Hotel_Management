package hotel.hotel_management.service.bar;

import hotel.hotel_management.modal.request.BarRequest;
import hotel.hotel_management.modal.response.Hotel.BarDTO;

import java.util.List;

public interface BarService {
    // get
    List<BarDTO> getBars();

    // create
    BarDTO addBar(BarRequest request);

    // update
    BarDTO updateBar(BarRequest request, int id);

    // hide open
    BarDTO hideOpenBar(int id);
}

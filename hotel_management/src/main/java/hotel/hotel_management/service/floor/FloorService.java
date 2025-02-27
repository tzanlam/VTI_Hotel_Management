package hotel.hotel_management.service.floor;

import hotel.hotel_management.modal.request.FloorRequest;
import hotel.hotel_management.modal.response.FloorDTO;

import java.util.List;

public interface FloorService {
    // get
    List<FloorDTO> findByHotelId(int hotelId);
    List<FloorDTO> findAll();
    FloorDTO findById(int id);

    // post
    FloorDTO createFloor(FloorRequest request);

    // put
    FloorDTO updateFloor(FloorRequest request, int id);

    // delete
    FloorDTO deleteFloor(int id);
}

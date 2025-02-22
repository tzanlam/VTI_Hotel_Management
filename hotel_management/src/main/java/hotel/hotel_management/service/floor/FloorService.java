package hotel.hotel_management.service.floor;

import hotel.hotel_management.modal.request.FloorRequest;
import hotel.hotel_management.modal.response.Hotel.FloorDTO;

import java.util.List;

public interface FloorService {
    List<FloorDTO> findByHotelId(int hotel_id);
    FloorDTO findById(int id);

    FloorDTO createNewFloor(FloorRequest request);

    FloorDTO updateFloor(FloorRequest request, int id);

    FloorDTO closeFloor(int id);
}

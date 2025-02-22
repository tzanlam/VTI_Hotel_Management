package hotel.hotel_management.service.room;

import hotel.hotel_management.modal.request.RoomRequest;
import hotel.hotel_management.modal.response.Hotel.RoomDTO;

public interface RoomService {
    RoomDTO findById(int id);

    RoomDTO createRoom(RoomRequest request);
}

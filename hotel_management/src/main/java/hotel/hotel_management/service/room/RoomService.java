package hotel.hotel_management.service.room;

import hotel.hotel_management.modal.request.RoomRequest;
import hotel.hotel_management.modal.response.RoomDTO;

import java.util.List;

public interface RoomService {
    // get
    List<RoomDTO> getAllRoom();
    List<RoomDTO> getByFloorId(int floorId);
    RoomDTO getByRoomId(int roomId);

    // post
    RoomDTO createRoom(RoomRequest request);

    // put
    RoomDTO updateRoom(RoomRequest request, int id);

    // make fixing
    RoomDTO changeStatus(int id, int status);

    // close_open
    RoomDTO deleteRoom(int id);
}

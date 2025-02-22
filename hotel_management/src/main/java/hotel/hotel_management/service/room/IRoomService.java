package hotel.hotel_management.service.room;

import hotel.hotel_management.modal.entity.hotel.Floor;
import hotel.hotel_management.modal.entity.hotel.Room;
import hotel.hotel_management.modal.request.RoomRequest;
import hotel.hotel_management.modal.response.Hotel.RoomDTO;
import hotel.hotel_management.repository.FloorRepository;
import hotel.hotel_management.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class IRoomService implements RoomService{
    private final RoomRepository roomRepository;
    private final FloorRepository floorRepository;

    public IRoomService(RoomRepository roomRepository, FloorRepository floorRepository) {
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
    }

    @Override
    public RoomDTO findById(int id) {
        Room room = roomRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Room not found")
        );
        return new RoomDTO(room);
    }

    @Override
    public RoomDTO createRoom(RoomRequest request) {
        Floor floor = floorRepository.findById(request.getFloorId()).orElseThrow(
                () -> new IllegalArgumentException("Floor not found")
        );
        Room room = request.setRoom();
        room.setFloor(floor);
        roomRepository.save(room);
        return new RoomDTO(room);
    }
}

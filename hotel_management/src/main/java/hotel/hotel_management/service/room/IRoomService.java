package hotel.hotel_management.service.room;

import hotel.hotel_management.modal.entity.Room;
import hotel.hotel_management.modal.request.RoomRequest;
import hotel.hotel_management.modal.response.RoomDTO;
import hotel.hotel_management.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IRoomService implements RoomService{
    private final RoomRepository roomRepository;

    public IRoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAllRoom() {
        return roomRepository.findAll().stream().map(RoomDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getByFloorId(int floorId) {
        List<Room> rooms = roomRepository.findByFloorId(floorId);
        if (Objects.nonNull(rooms)) {
            return rooms.stream().map(RoomDTO::new).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public RoomDTO getByRoomId(int roomId) {
        return new RoomDTO(roomRepository.findById(roomId).orElseThrow(
                () -> new RuntimeException("Room not found")
        ));
    }

    @Override
    public RoomDTO createRoom(RoomRequest request) {
        Room room = request.addRoom();
        roomRepository.save(room);
        return new RoomDTO(room);
    }

    @Override
    public RoomDTO updateRoom(RoomRequest request, int id) {
        Room room = roomRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Room not found")
        );
        request.updateRoom(room);
        roomRepository.save(room);
        return new RoomDTO(room);
    }

    @Override
    public RoomDTO changeStatus(int id, int status) {
        Room room = roomRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Room not found")
        );
        switch (status) {
            case 1:
                room.setStatus(Room.StatusRoom.AVAILABLE);
                break;
            case 2:
                room.setStatus(Room.StatusRoom.FIXING);
                break;
        }
        roomRepository.save(room);
        return new RoomDTO(room);
    }

    @Override
    public RoomDTO deleteRoom(int id) {
        Room room = roomRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Room not found")
        );
        if (room.getStatus() == Room.StatusRoom.AVAILABLE) {
            room.setStatus(Room.StatusRoom.DELETED);
        }else {
            room.setStatus(Room.StatusRoom.AVAILABLE);
        }
        roomRepository.save(room);
        return new RoomDTO(room);
    }
}
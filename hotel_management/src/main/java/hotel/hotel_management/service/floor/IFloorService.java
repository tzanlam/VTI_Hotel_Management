package hotel.hotel_management.service.floor;

import hotel.hotel_management.modal.constant.StatusOL;
import hotel.hotel_management.modal.entity.Floor;
import hotel.hotel_management.modal.entity.Hotel;
import hotel.hotel_management.modal.request.FloorRequest;
import hotel.hotel_management.modal.response.FloorDTO;
import hotel.hotel_management.repository.FloorRepository;
import hotel.hotel_management.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IFloorService implements FloorService{
    private final FloorRepository floorRepository;
    private final HotelRepository hotelRepository;

    public IFloorService(FloorRepository floorRepository, HotelRepository hotelRepository) {
        this.floorRepository = floorRepository;
        this.hotelRepository = hotelRepository;
    }


    @Override
    public List<FloorDTO> findByHotelId(int hotelId) {
        return floorRepository.findByHotelId(hotelId).stream().map(FloorDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<FloorDTO> findAll() {
        return floorRepository.findAll().stream().map(FloorDTO::new).collect(Collectors.toList());
    }

    @Override
    public FloorDTO findById(int id) {
        Floor floor = floorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid id: " + id)
        );
        return new FloorDTO(floor);
    }

    @Override
    public FloorDTO createFloor(FloorRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(
                () -> new IllegalArgumentException("Invalid hotelId: " + request.getHotelId())
        );
        Floor floor = new Floor();
        floor.setName(request.getName());
        floor.setHotel(hotel);
        floor.setStatus(StatusOL.OPEN);
        floorRepository.save(floor);
        return new FloorDTO(floor);
    }

    @Override
    public FloorDTO updateFloor(FloorRequest request, int id) {
        Floor floor = floorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid id: " + id)
        );
        floor.setName(request.getName());
        floorRepository.save(floor);
        return new FloorDTO(floor);
    }

    @Override
    public FloorDTO deleteFloor(int id) {
        Floor floor = floorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid id: " + id)
        );
        if (floor.getStatus().equals(StatusOL.OPEN)) {
            floor.setStatus(StatusOL.CLOSED);
        } else {
            floor.setStatus(StatusOL.OPEN);
        }
        floorRepository.save(floor);
        return new FloorDTO(floor);
    }
}

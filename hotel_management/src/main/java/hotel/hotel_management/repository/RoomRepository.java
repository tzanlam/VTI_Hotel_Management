package hotel.hotel_management.repository;

import hotel.hotel_management.modal.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("select r from Room r join Floor f where f.id = :floorId")
    List<Room> findByFloorId(@Param("floorId") int floorId);

}

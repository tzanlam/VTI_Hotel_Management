package hotel.hotel_management.service.booked;

import hotel.hotel_management.methodSupport.Method;
import hotel.hotel_management.modal.entity.*;
import hotel.hotel_management.modal.request.BookedRequest;
import hotel.hotel_management.modal.response.BookedDTO;
import hotel.hotel_management.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class IBookedServiceImpl implements BookedService {
    private final BookedRepository bookedRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    public IBookedServiceImpl(BookedRepository bookedRepository, RoomRepository roomRepository, GuestRepository guestRepository) {
        this.bookedRepository = bookedRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }


    @Override
    public List<BookedDTO> getBookedByHotel(int hotelId) {
        return bookedRepository.findByHotelId(hotelId).stream().map(BookedDTO::new).collect(Collectors.toList());
    }

    @Override
    public BookedDTO getById(int bookedId) {
        Booked booked = bookedRepository.findById(bookedId).orElseThrow(
                ()-> new NullPointerException("Booked with id " + bookedId + " not found.")
        );
        return new BookedDTO(booked);
    }

    @Override
    public List<BookedDTO> searchBySpec(String spec, String... fields) {
        Specification<Booked> combinedSpec = Specification.where(null);
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        for (String field : fields) {
            Specification<Booked> specField = Method.searchBySpec(field, spec);
            combinedSpec = combinedSpec.or(specField);
        }

        List<Booked> bookedList = bookedRepository.findAll(combinedSpec, sort);

        if (bookedList.isEmpty()) {
            throw new NullPointerException("Null value with request: " + spec);
        }

        return bookedList.stream().map(BookedDTO::new).collect(Collectors.toList());
    }

    @Override
    public BookedDTO createBooked(BookedRequest request, boolean isBooked) {
        Booked booked = request.addBooked();
        Guest guest = guestRepository.findByFullName(request.getGuestName()).orElseThrow(
                () -> new NullPointerException("Guest with name " + request.getGuestName() + " not found.")
        );
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(
                () -> new NullPointerException("Room with id " + request.getRoomId() + " not found.")
        );
        booked.getGuest().add(guest);
        booked.getRoom().add(room);
        request.setBooked(isBooked);
        bookedRepository.save(booked);
        return new BookedDTO(booked);
    }

    @Override
    public BookedDTO updateBooked(int bookedId, BookedRequest request) {
        Booked booked = bookedRepository.findById(bookedId).orElseThrow(
                () -> new NullPointerException("Booked with id " + bookedId + " not found.")
        );
        request.updateBooked(booked);
        bookedRepository.save(booked);
        return new BookedDTO(booked);
    }

    @Override
    public String deleteBooked(int bookedId) {
        Booked booked = bookedRepository.findById(bookedId).orElseThrow(
                () -> new NullPointerException("Booked with id " + bookedId + " not found")
        );
        booked.setStatus(Booked.StatusBooked.CANCELLED);
        bookedRepository.save(booked);
        return "Booking status updated successfully";
    }
}

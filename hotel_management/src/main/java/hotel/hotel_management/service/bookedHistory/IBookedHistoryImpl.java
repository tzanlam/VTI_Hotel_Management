package hotel.hotel_management.service.bookedHistory;

import hotel.hotel_management.modal.entity.Account;
import hotel.hotel_management.modal.entity.Booked;
import hotel.hotel_management.modal.entity.BookedHistory;
import hotel.hotel_management.modal.response.BookedHistoryDTO;
import hotel.hotel_management.repository.AccountRepository;
import hotel.hotel_management.repository.BookedHistoryRepository;
import hotel.hotel_management.repository.BookedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IBookedHistoryImpl implements BookedHistoryService {
    private final BookedHistoryRepository bookedHistoryRepository;
    private final BookedRepository bookedRepository;
    private final AccountRepository accountRepository;

    public IBookedHistoryImpl(BookedHistoryRepository bookedHistoryRepository, BookedRepository bookedRepository, AccountRepository accountRepository) {
        this.bookedHistoryRepository = bookedHistoryRepository;
        this.bookedRepository = bookedRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<BookedHistoryDTO> findHistories() {
        return bookedHistoryRepository.findAll().stream().map(BookedHistoryDTO::new).collect(Collectors.toList());
    }

    @Override
    public BookedHistoryDTO findBookedHistoryById(int id) {
        return new BookedHistoryDTO(bookedHistoryRepository.findById(id).orElseThrow(
                () -> new NullPointerException("BookedHistory not found")
        ));
    }

    @Override
    public List<BookedHistoryDTO> findByHotelId(int hotelId) {
        return bookedHistoryRepository.findByHotelId(hotelId).stream().map(BookedHistoryDTO::new).collect(Collectors.toList());
    }

    @Override
    public BookedHistoryDTO actionByEmployee(int bookedId, int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new NullPointerException("Account not found")
        );
        Booked booked = bookedRepository.findById(bookedId).orElseThrow(
                () -> new NullPointerException("Booked not found")
        );
        BookedHistory bookedHistory = new BookedHistory();
        bookedHistory.setBooked(booked);
        bookedHistory.setAccount(account);
        switch (booked.getStatus()){
            case Booked.StatusBooked.BOOKED:{
                bookedHistory.setAction(BookedHistory.Action.BOOKING);
            }
            case Booked.StatusBooked.STAY:{
                bookedHistory.setAction(BookedHistory.Action.CHECKIN);
            }
            bookedHistoryRepository.save(bookedHistory);
        }
        return new BookedHistoryDTO(bookedHistory);
    }
}

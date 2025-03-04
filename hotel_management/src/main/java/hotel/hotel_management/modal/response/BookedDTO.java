package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.Booked;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class BookedDTO {
    private int id;
    private List<BookedHistoryDTO> bookedHistories;
    private List<GuestDTO> guests;
    private List<RoomDTO> rooms;
    private List<ServiceDTO> services;
    private List<PaymentDTO> payments;
    private String typeCheckIn;
    private String typeStay;
    private String checkIn;
    private String checkOut;
    private String totalPrice;
    private String status;

    public BookedDTO(Booked booked) {
        this.id = booked.getId();
        this.bookedHistories = Objects.nonNull(booked.getHistories()) ? booked.getHistories()
                .stream().map(BookedHistoryDTO::new).collect(Collectors.toList()) : new ArrayList<>();
        this.guests = Objects.nonNull(booked.getGuest()) ? booked.getGuest()
                .stream().map(GuestDTO::new).collect(Collectors.toList()): new ArrayList<>();
        this.payments = Objects.nonNull(booked.getPayments()) ? booked.getPayments()
                .stream().map(PaymentDTO::new).collect(Collectors.toList()) : new ArrayList<>();
        this.rooms = Objects.nonNull(booked.getRoom()) ? booked.getRoom()
                .stream().map(RoomDTO::new).collect(Collectors.toList()): new ArrayList<>();
        this.services = Objects.nonNull(booked.getService()) ? booked.getService()
                .stream().map(ServiceDTO::new).collect(Collectors.toList()): new ArrayList<>();
        this.typeCheckIn = String.valueOf(booked.getCheckIn());
        this.typeStay = String.valueOf(booked.getCheckOut());
        this.checkIn = String.valueOf(booked.getCheckIn());
        this.checkOut = String.valueOf(booked.getCheckOut());
        this.totalPrice = String.valueOf(booked.getTotalPrice());
        this.status = String.valueOf(booked.getStatus());
    }
}

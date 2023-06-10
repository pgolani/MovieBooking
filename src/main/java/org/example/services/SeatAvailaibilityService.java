package org.example.services;

import lombok.NonNull;
import org.example.model.BookingStatus;
import org.example.model.Seat;
import org.example.model.SeatLock;
import org.example.model.Show;
import org.example.providers.SeatLockProvider;
import org.example.providers.SeatLockProviderImpl;

import java.util.List;
import java.util.Map;

public class SeatAvailaibilityService{

    private final SeatLockProvider seatLockProvider;
    private final BookingService bookingService;

    public SeatAvailaibilityService(@NonNull final  SeatLockProvider seatLockProvider , @NonNull final BookingService bookingService){
        this.seatLockProvider=seatLockProvider;
        this.bookingService=bookingService;
    }


    public List<Seat> getAvailableSeats(Show show){
        List<Seat> allSeats = show.getScreen().getSeats();
        allSeats.removeAll(getUnavailableSeats(show));
        return allSeats;
    }

    public List<Seat> getUnavailableSeats(Show show){
        List<Seat> booked = bookingService.getBookedSeats(show);
        List<Seat> locked = seatLockProvider.getLockedSeats(show);
        booked.addAll(locked);
        return booked;

    }
}

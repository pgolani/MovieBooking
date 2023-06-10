package org.example.services;

import org.example.exceptions.SeatNotFoundException;
import org.example.exceptions.SeatPermanentNotAvailable;
import org.example.model.Booking;
import org.example.model.Seat;
import org.example.model.Show;
import org.example.providers.SeatLockProvider;

import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {

    Map<String , Booking > bookings;
    private final SeatLockProvider seatLockProvider;

    public BookingService(SeatLockProvider seatLockProvider){
        this.bookings = new HashMap<>();
        this.seatLockProvider=seatLockProvider;
    }
    public Booking getBookingById(String id){
        Booking booking = bookings.get(id);
        return booking;
    }

    public Booking createBooking(Show show ,String userId , List<Seat> seatsBooked) {
        if(isAnyseatAlreadyBooked(show , seatsBooked)){
            throw new SeatPermanentNotAvailable();
        }
        String id = UUID.randomUUID().toString();
        seatLockProvider.lockSeats(seatsBooked,show , userId);
        Booking booking = new Booking(id,userId , show ,seatsBooked);
        bookings.put(id,booking);

        return booking;
    }

    public List<Seat> getBookedSeats(Show show){
        return getAllBookings(show).stream().filter(Booking::isConfirmBooking)
                .map(Booking::getSeatsbooked)
                .flatMap(list-> list.stream())
                .collect(Collectors.toList());
    }

    public List<Booking> getAllBookings(Show show){
            List<Booking>res =new ArrayList<>();
            for(Booking b: bookings.values()){
                if(b.getShow().equals(show)){
                    res.add(b);
                }
            }
            return res;
    }

    public void confirmBooking(Booking booking){
        booking.confirmBooking();
    }
    public boolean isAnyseatAlreadyBooked(Show show , List<Seat> seats){
                List<Seat>booked = getBookedSeats(show);
                for(Seat s: seats){
                    if(booked.contains(s))return true;
                }
                return false;
    }
}

package org.example.api;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.model.Booking;
import org.example.model.Seat;
import org.example.model.Show;
import org.example.services.BookingService;
import org.example.services.ShowService;
import org.example.services.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final ShowService showService;
    private final TheatreService theatreService;

    public String createBooking(@NonNull final String showId , @NonNull final String userId , @NonNull final List<String> seatsbookedId) {
            Show show = showService.getShow(showId);
            List<Seat> seatsBooked = seatsbookedId.stream().map(theatreService :: getSeat).collect(Collectors.toList());
            return bookingService.createBooking(show ,userId ,seatsBooked).getBookingId();

    }

}

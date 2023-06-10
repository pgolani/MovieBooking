package org.example.api;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.model.*;
import org.example.services.MovieService;
import org.example.services.SeatAvailaibilityService;
import org.example.services.ShowService;
import org.example.services.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowController {
    private final ShowService showService;
    private final MovieService movieService;
    private final TheatreService theatreService;
    private final SeatAvailaibilityService seatAvailaibilityService;


    public String createShow(@NonNull final  String MovieId , @NonNull final Integer Duration , @NonNull final Date starttime , @NonNull final String screenId){
        Movie movie = movieService.getMovie(MovieId);
        Screen screen = theatreService.getScreen(screenId);
        //List<Seat>seats = seatsId.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return showService.createShow(Duration , movie  , starttime , screen).getShowId();

    }
    public List<String> getAvailabeSeats(String showId){
        Show show = showService.getShow(showId);
        List<Seat> Aseats= seatAvailaibilityService.getAvailableSeats(show);
        return Aseats.stream().map(Seat::getSeatId).collect(Collectors.toList());
    }


}

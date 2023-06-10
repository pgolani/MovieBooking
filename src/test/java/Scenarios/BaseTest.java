package Scenarios;

import org.example.api.*;
import org.example.providers.SeatLockProvider;
import org.example.providers.SeatLockProviderImpl;
import org.example.services.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

public class BaseTest {
    protected BookingController bookingController;
    protected MovieController movieController;
    protected ShowController showController;
    protected PaymentController paymentController;
    protected TheatreController theatreController;

    public void setupControllers(Integer duration , Integer retryNo){
       final SeatLockProviderImpl seatLockProvider = new SeatLockProviderImpl(duration);

        final BookingService bookingService = new BookingService(seatLockProvider);
        final MovieService movieService= new MovieService();
        final ShowService showService = new ShowService();
        final TheatreService theatreService = new TheatreService();
        final PaymentService paymentService=new PaymentService(retryNo , seatLockProvider);
        final SeatAvailaibilityService seatAvailaibilityService = new SeatAvailaibilityService(seatLockProvider,bookingService);

        bookingController = new BookingController(bookingService,showService,theatreService);
        showController = new ShowController(showService,movieService,theatreService ,seatAvailaibilityService);
        movieController=new MovieController(movieService);
        theatreController=new TheatreController(theatreService);
        paymentController=new PaymentController(paymentService,bookingService);
    }
    protected void validateSeatsList(List<String> seats ,List<String>allSeatsInScreen , List<String>excludedSeats){
            for(String seat : allSeatsInScreen){
                if(!excludedSeats.contains(seat)){
                    Assert.assertTrue(seats.contains(seat));
                }
            }
            for(String seat : excludedSeats){
                Assert.assertFalse(seats.contains(seat));
            }
    }
    protected List<String> createSeats(String screen , Integer row , Integer seatsInrow , TheatreController theatreController){
        List<String>seats =new ArrayList<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<seatsInrow;j++){
                String seat = theatreController.createSeatInScreen(i,j,screen);
                seats.add(seat);
            }
        }
        return  seats;
    }
    protected String setupScreen(){
            final String theatre = theatreController.createTheatre("Theatre1");
            final String screen = theatreController.createScreenInTheatre("Screen1", theatre);
            return screen;
    }
}

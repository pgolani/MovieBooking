package Scenarios;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCase2 extends BaseTest{
    @BeforeEach
    public void setup(){
        setupControllers(10,0);
    }
    @Test
    public void TestCase(){
        String user1 = "User1";
        String user2 = "User2";
        String screen = setupScreen();

        String movie = movieController.createMovie("Movie2");
        List<String> seats = createSeats(screen,2,10,theatreController);
        String show = showController.createShow(movie,2*60*60 , new Date(), screen);
        List<String> u1AvailableSeats = showController.getAvailabeSeats(show);
        validateSeatsList(u1AvailableSeats,seats ,ImmutableList.of());




        ImmutableList<String> u1SelectedSeats = ImmutableList.of(seats.get(0),seats.get(5));
        String booking = bookingController.createBooking(show,user1,u1SelectedSeats);
        List<String> u2AvailableSeats = showController.getAvailabeSeats(show);
        validateSeatsList(u2AvailableSeats,seats,u1SelectedSeats);

        paymentController.processFailedPayment(booking,user1);

        final List<String> u2AvailableSeatsAfterFailedPayment = showController.getAvailabeSeats(show);

        validateSeatsList(u2AvailableSeatsAfterFailedPayment ,seats ,ImmutableList.of());
    }
}

package Scenarios;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCase1 extends BaseTest{
    @BeforeEach
    public void setup(){
        setupControllers(10,0);
    }
    @Test
    public void TestCase(){
        String user1= "User1";
        String user2= "User2";
        final String screen = setupScreen();
        final List<String> seatsInScreen = createSeats(screen,2,10,theatreController);
        final String movie = movieController.createMovie("Movie1");
        final String show = showController.createShow(movie,2*60*6, new Date(),screen);
        List<String> u1seatsAvailable = showController.getAvailabeSeats(show);

        validateSeatsList(u1seatsAvailable,seatsInScreen,new ArrayList<>());
        List<String> u1selectedSeats = new ArrayList<>();
        u1selectedSeats.add(seatsInScreen.get(0));
        u1selectedSeats.add(seatsInScreen.get(5));

        String booking = bookingController.createBooking(show , user1 ,u1selectedSeats);
        paymentController.processSuccessPayment(booking);

        List<String>u2SeatsAvaiable = showController.getAvailabeSeats(show);

        validateSeatsList(u2SeatsAvaiable , seatsInScreen , u1selectedSeats);

    }

}

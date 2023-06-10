package Scenarios;

import com.google.common.collect.ImmutableList;
import org.example.exceptions.SeatPermanentNotAvailable;
import org.example.exceptions.SeatTemporayNotAvailableException;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.List;

public class TestCase3 extends BaseTest{
            public void setup(){
                setupControllers(10,0);
            }
            public void TestCase(){
                String movie = movieController.createMovie("Movie3");
                String screen = setupScreen();
                List<String> seatsInScreen = createSeats(screen,2,10,theatreController);
                String user1= "USer1";
                String user2= "User2";
                String show = showController.createShow(movie ,2*60*60 , new Date(), screen);

                List<String>u1AvailableSeats= showController.getAvailabeSeats(show);
                validateSeatsList(u1AvailableSeats,seatsInScreen, ImmutableList.of());

                ImmutableList<String> u1SelectedSeats = ImmutableList.of(
                        seatsInScreen.get(0),
                        seatsInScreen.get(1)
                );

                ImmutableList<String> u2SelectedSeats = ImmutableList.of(
                        seatsInScreen.get(0),
                        seatsInScreen.get(2)
                );
                final String u1Booking = bookingController.createBooking(show,user1,u1SelectedSeats);

                Assertions.assertThrows(SeatTemporayNotAvailableException.class , ()->{
                    final String u2bookingId = bookingController.createBooking(show,user2,u2SelectedSeats);
                });

                paymentController.processSuccessPayment(u1Booking);

                Assertions.assertThrows(SeatPermanentNotAvailable.class , ()->{
                    final String u2bookingId = bookingController.createBooking(show ,screen ,u2SelectedSeats);
                });




















            }

}

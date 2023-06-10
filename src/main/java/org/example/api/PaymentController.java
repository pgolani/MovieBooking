package org.example.api;

import org.example.model.Booking;
import org.example.services.BookingService;
import org.example.services.PaymentService;

public class PaymentController {

            private final PaymentService paymentService;
            private final BookingService bookingService;

            public PaymentController(PaymentService paymentService , BookingService bookingService){
                this.paymentService= paymentService;
                this.bookingService=bookingService;
            }

            public void processSuccessPayment(String bookingId){
                Booking booking =bookingService.getBookingById(bookingId);
                bookingService.confirmBooking(booking);

            }
            public void processFailedPayment(String bookingId , String userId){
                Booking booking =bookingService.getBookingById(bookingId);
                paymentService.processFailed(booking,userId);

            }


}

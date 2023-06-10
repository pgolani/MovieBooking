package org.example.model;

import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.List;

@Getter
public class Booking {

    private final String bookingId;
    private final Instant created;

    private final List<Seat> seatsbooked;
    private final Show show;
    private BookingStatus status;
    private String userId;


    public  Booking (@NonNull final  String id , @NonNull final  String userId ,@NonNull final Show show , final @NonNull List<Seat >seatsbooked){
        this.bookingId=id;
        this.status= BookingStatus.Created;
        this.userId=userId;
        this.created = Instant.now();
        this.show=show;
        this.seatsbooked=seatsbooked;

    }
    public boolean isConfirmBooking(){
        return this.status.equals(BookingStatus.Confirmed);
    }
    public void confirmBooking(){
        this.status=BookingStatus.Confirmed;
    }
    public void expireBooking(){
        this.status=BookingStatus.Expired;
    }


}

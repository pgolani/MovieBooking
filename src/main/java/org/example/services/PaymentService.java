package org.example.services;

import org.example.model.Booking;
import org.example.providers.SeatLockProvider;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {

        private final Integer retryLimit;
        private final Map<Booking,Integer> allowedMap;
        private final SeatLockProvider seatLockProvider;
        public PaymentService(Integer retryLimit , SeatLockProvider seatLockProvider){
            this.retryLimit = retryLimit;
            this.seatLockProvider= seatLockProvider;
            allowedMap=new HashMap<>();
        }


            public void processFailed(Booking booking , String userId){
                    if(!allowedMap.containsKey(booking)){
                        allowedMap.put(booking,0);
                    }
                    final Integer currentretry = allowedMap.get(booking);
                    final Integer newretry = currentretry+1;
                    allowedMap.put(booking ,newretry);
                    if(newretry>retryLimit){
                        booking.expireBooking();
                        seatLockProvider.unlockSeats( booking.getSeatsbooked(),booking.getShow(), userId);
                    }
            }
}

package org.example.providers;

import lombok.NonNull;
import org.example.exceptions.SeatTemporayNotAvailableException;
import org.example.model.Seat;
import org.example.model.SeatLock;
import org.example.model.Show;

import java.util.*;

public class SeatLockProviderImpl implements SeatLockProvider{

    private final Map<Show ,Map<Seat , SeatLock>> seatLocks;
    private final Integer timeduration;
    public SeatLockProviderImpl(Integer timeduration){
        this.seatLocks= new HashMap<>();
        this.timeduration =timeduration;
    }

    @Override
    public void lockSeats(List<Seat> seats, Show show , String userId ) {
                for(Seat s : seats){
                    if(isSeatLocked(s,show)){
                        throw new SeatTemporayNotAvailableException();
                    }
                }
                for(Seat seat: seats){
                    lockSeat(seat , show ,userId ,timeduration);
                }
    }

    public void lockSeat(Seat seat , Show show ,String userId , Integer timeduration){
        if(!seatLocks.containsKey(show)){
            seatLocks.put(show, new HashMap<>());
        }
        final SeatLock seatLock = new SeatLock(timeduration, seat ,show, new Date(),userId);
        seatLocks.get(show).put(seat,seatLock);
    }

    public void unlockSeats(List<Seat> seats ,Show show ,String userId){
        for(Seat seat : seats){
            unlockSeat(seat,show,userId);
        }
    }

    public void unlockSeat(Seat seat , Show show ,String userId){
        if(seatLocks.containsKey(show)){
            if(seatLocks.get(show).containsKey(seat)){
                seatLocks.get(show).remove(seat);
            }
        }
    }

    public List<Seat> getLockedSeats(@NonNull final  Show show){
        List<Seat> lockedSeats= new ArrayList<>();
            if(seatLocks.containsKey(show)){
                    for(Seat seat : seatLocks.get(show).keySet()){
                        if(isSeatLocked(seat,show)){
                            lockedSeats.add(seat);
                        }
                    }
            }
            return lockedSeats;
    }

    public boolean isSeatLocked(Seat seat ,Show show){
        return seatLocks.containsKey(show) && seatLocks.get(show).containsKey(seat) && !seatLocks.get(show).get(seat).isLockExpired();
    }
}

package org.example.model;

import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
public class SeatLock {

    private Integer timeduration;
    private final Seat seat;
    private final Show show;
    private final Date lockTime;
    private final String lockedBy;

    public boolean isLockExpired(){
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeduration);
        final Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }

}

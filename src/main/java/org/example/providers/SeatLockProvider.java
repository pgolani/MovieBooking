package org.example.providers;

import org.example.model.Seat;
import org.example.model.Show;

import java.util.List;

public interface SeatLockProvider {

    public void lockSeats(List<Seat> seats, Show show ,String userId);

    public void unlockSeats(List<Seat> seats, Show show, String userId);

    public List<Seat> getLockedSeats(Show show);
}

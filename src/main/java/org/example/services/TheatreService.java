package org.example.services;

import lombok.AllArgsConstructor;
import org.example.model.Screen;
import org.example.model.Seat;
import org.example.model.Show;
import org.example.model.Theatre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class TheatreService {

    private final Map<String, Seat> seats;
    private final Map<String , Theatre>theatres;
    private final Map<String, Screen> screens;
    public TheatreService(){
        this.screens=new HashMap<>();
        this.theatres=new HashMap<>();
        this.seats=new HashMap<>();
    }


    public Seat getSeat(String id){
        return seats.get(id);
    }
    public Theatre getTheatre(String id){
        return theatres.get(id);
    }
    public Screen getScreen(String id){
        return screens.get(id);
    }
    public Theatre createTheatre ( String name){
        String id = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(id ,name);
        theatres.put(id,theatre);
        return theatre;

    }
    public Screen createScreenInTheatre(String name , Theatre theatre){
        String id= UUID.randomUUID().toString();
        Screen screen = new Screen(id,name ,theatre);
        screens.put(id,screen);
        theatre.addScreen(screen);
        return  screen;
    }

    public Seat createSeatInScreen(int rowNo , int seatNo, Screen screen){
        String id= UUID.randomUUID().toString();
        Seat seat = new Seat(id, rowNo ,seatNo);
        seats.put(id,seat);
        screen.addSeat(seat);
        return  seat;
    }
}

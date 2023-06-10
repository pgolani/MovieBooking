package org.example.api;

import lombok.AllArgsConstructor;
import org.example.model.Screen;
import org.example.model.Theatre;
import org.example.services.TheatreService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TheatreController {
    private final TheatreService theatreService;


    public String createTheatre(String name){
        return theatreService.createTheatre(name).getTheatreId();
    }
    public String createScreenInTheatre(String name , String theatreId){
        Theatre theatre = theatreService.getTheatre(theatreId);
        return theatreService.createScreenInTheatre(name , theatre).getScreenId();
    }
    public String createSeatInScreen(int rowNo , int seatNo , String screenId){
        Screen screen = theatreService.getScreen(screenId);
        return  theatreService.createSeatInScreen(rowNo,seatNo,screen).getSeatId();
    }
}

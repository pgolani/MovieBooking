package org.example.services;

import org.example.model.Movie;
import org.example.model.Screen;
import org.example.model.Seat;
import org.example.model.Show;

import java.util.*;

public class ShowService {

    private final Map<String , Show> shows;

    public ShowService(){
        this.shows=new HashMap<>();
    }

    public Show getShow(String id){
        return shows.get(id);
    }
    public Show createShow(Integer duration , Movie movie , Date starttime , Screen screen){
        String id = UUID.randomUUID().toString();
        Show show = new Show(id, starttime,duration,movie ,screen);
        this.shows.put(id,show);
        return show;
    }
     private List<Show> getShowForScreen(Screen screen){
        List<Show>res = new ArrayList<>();
                for(Show s : shows.values()){
                    if(s.getScreen().equals(screen)){
                        res.add(s);
                    }
                }
                return res;
     }
}

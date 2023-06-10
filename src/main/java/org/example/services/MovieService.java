package org.example.services;

import lombok.AllArgsConstructor;
import org.example.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class MovieService {

    private final Map<String, Movie> movies;
    public MovieService(){
        this.movies=new HashMap<>();
    }

    public Movie getMovie(String id){
        return movies.get(id);
    }

    public Movie createMovie(String name){
        String id = UUID.randomUUID().toString();
        Movie movie = new Movie(id , name );
        movies.put(id,movie);
        return movie;
    }
}

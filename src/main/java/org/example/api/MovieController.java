package org.example.api;

import lombok.AllArgsConstructor;
import org.example.services.MovieService;

@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    public String createMovie(String name ){
        return movieService.createMovie(name).getMovieId();
    }
}

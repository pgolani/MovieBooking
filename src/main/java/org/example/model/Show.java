package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Getter
public class Show {

    private String showId;
    private Date starttime;
    private Integer duration;

    private Movie movie;
    private Screen screen;

}

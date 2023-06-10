package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Screen {

    private final String screenId;
    private  final String name;
    private final Theatre theatre;
    private final List<Seat> seats;

    public Screen(@NonNull final  String screenId , @NonNull final  String name , @NonNull final Theatre theatre){
        this.screenId=screenId;
        this.name=name;
        this.theatre=theatre;
        this.seats= new ArrayList<>();
    }
    public void addSeat(@NonNull final  Seat seat){
        this.seats.add(seat);
    }

}

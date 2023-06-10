package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Seat {

    private final String seatId;
    private final int rowNO;

    private final int seatNo;


}

package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


@Getter

public class Theatre {
    private String theatreId;

    private List<Screen> screens;
    private String Tname;

    public Theatre(@NonNull final String Tname, @NonNull final String id){
        this.Tname=Tname;
        this.theatreId= id;
        this.screens=new ArrayList<>();
    }

    public void addScreen(@NonNull final Screen screen){
        this.screens.add(screen);
    }

}

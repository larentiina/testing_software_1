package larentina.task3.science;

import larentina.task3.space.Planet;
import larentina.task3.space.SpaceBody;
import larentina.task3.space.Star;


import java.util.ArrayList;
import java.util.List;


public class Report {
    private static List<String> notes = new ArrayList<>();

    public static void createNote(SpaceBody body) {
        if(body instanceof Planet)
            notes.add("Планета с координатами " + body.getCoordinates().toString() + ", состояние планеты: " + ((Planet) body).getState());
        else if(body instanceof Star){
            notes.add("Звезда с координатами " + body.getCoordinates().toString() + ", состояние звезды: " + ((Star) body).getState());
            notes.add(" цвет звезды: " + ((Star) body).getColor());
        }

    }

    public static List<String> getNotes(){
        return notes;
    }

    public static void makeEmptyNotes(){
        notes = new ArrayList<>();
    }

}

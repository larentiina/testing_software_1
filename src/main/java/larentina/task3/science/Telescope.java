package larentina.task3.science;

import larentina.task3.space.Space;
import larentina.task3.space.SpaceBody;
import lombok.Data;
import lombok.Getter;

import java.util.List;


public class Telescope {
    private Space space;

    public Telescope(Space space) {
        this.space = space;
    }

    public List<SpaceBody> activate(){
        return space.getBodies();
    }

}

package larentina.task3.space;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public abstract class SpaceBody {
    private Coordinates coordinates;
    private float radius;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public float getRadius() {
        return radius;
    }
}

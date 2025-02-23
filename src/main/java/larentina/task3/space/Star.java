package larentina.task3.space;


import larentina.task3.space.state.Color;
import larentina.task3.space.state.StarState;
import lombok.Getter;
import lombok.Setter;


public class Star extends SpaceBody {
    private Color color;
    private StarState state;

    public Star(Coordinates coordinates, float radius, larentina.task3.space.state.Color color) {
        super(coordinates, radius);
        this.color = color;
        this.state = StarState.STARTING;
    }

    public void light(Color color) {
        this.color = color;
        this.state = StarState.LIGHTING;
    }

    public StarState getState() {
        return state;
    }

    public void setState(StarState state) {
        this.state = state;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

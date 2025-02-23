package larentina.task3.space;

import larentina.task3.space.state.Color;
import larentina.task3.space.state.PlanetState;
import larentina.task3.space.state.StarState;



public class Planet extends SpaceBody{
    private float moveSpeed;
    private PlanetState state;

    public Planet(Coordinates coordinates, float radius,float moveSpeed) {
        super(coordinates, radius);
        this.moveSpeed = moveSpeed;
        this.state = PlanetState.STARTING;
    }

    public void move(){
        setState(PlanetState.MOVING);
        setCoordinates(new Coordinates(getCoordinates().getX()+moveSpeed, getCoordinates().getY()));
    }

    public boolean checkCollisionWithStar(Star star) {
        double distanceToStar = Math.sqrt(Math.pow(star.getCoordinates().getX() - this.getCoordinates().getX(), 2) +
                Math.pow(star.getCoordinates().getY() - this.getCoordinates().getY(), 2));
        if(distanceToStar <= star.getRadius() / 1.3)
        {
            return true;
        }
        else setState(PlanetState.MOVING);
        return false;
    }

    public void castShadow(Star star) {
        setState(PlanetState.SHADOW);
        star.setColor(Color.BLACK);
        star.setState(StarState.SHADOWING);
    }

    public void checkBoundsAndWrap(double width) {
        double x = getCoordinates().getX();
        double y = getCoordinates().getY();
        if (x  > width) {
            setState(PlanetState.OUT_OF_BOUNDS);
            setCoordinates(new Coordinates(0, y));

        } else if (x + getRadius() < 0) {
            setState(PlanetState.OUT_OF_BOUNDS);
            setCoordinates(new Coordinates(width - getRadius(), y));
        }

    }

    public PlanetState getState() {
        return state;
    }

    public void setState(PlanetState state) {
        this.state = state;
    }
}

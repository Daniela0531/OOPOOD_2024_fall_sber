package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Step {
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;

    public Step(GridPoint2 coordinates, GridPoint2 destinationCoordinates) {
        this.coordinates = coordinates;
        this.destinationCoordinates = destinationCoordinates;
    }
    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }
}

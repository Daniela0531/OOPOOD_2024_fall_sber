package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Movement {
    private GridPoint2 destinationCoordinates;
    private float progress = 1f;
    private float rotation;

    public Movement(GridPoint2 playerDestinationCoordinates, float playerRotation) {
        this.rotation = playerRotation;
        this.destinationCoordinates = playerDestinationCoordinates;
    }

    public float getProgress() {
        return progress;
    }

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}

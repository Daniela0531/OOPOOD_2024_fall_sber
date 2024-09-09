package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class PlayerMovement {
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private GridPoint2 coordinates;
    // which tile the player want to go next
    private GridPoint2 destinationCoordinates;
    private float progress = 1f;
    private float rotation;

    public PlayerMovement(GridPoint2 playerCoordinates, GridPoint2 playerDestinationCoordinates, float playerRotation) {
        this.coordinates = playerCoordinates;
        this.rotation = playerRotation;
        this.destinationCoordinates = playerDestinationCoordinates;
    }

    public float getProgress() {
        return progress;
    }

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates = coordinates;
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

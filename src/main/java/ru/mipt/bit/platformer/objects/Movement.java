package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Movement {
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private GridPoint2 coordinates;
    // which tile the player want to go next
    private GridPoint2 destinationCoordinates;
    private float progress = 1f;
    private float rotation;

    private Obstacle treeObstacle;

    public Movement(GridPoint2 playerDestinationCoordinates, float playerRotation, GridPoint2 tankCoordinates, Obstacle treeObstacle) {
        this.coordinates = tankCoordinates;
        this.rotation = playerRotation;
        this.destinationCoordinates = playerDestinationCoordinates;
        this.treeObstacle = treeObstacle;
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

//    public void setCoordinates(GridPoint2 coordinates) {
//        this.coordinates = coordinates;
//    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public Obstacle getTreeObstacle() {
        return treeObstacle;
    }

    public void doStep(GridPoint2 step) {
        if (isEqual(progress, 1f)) {
            // check potential player destination for collision with obstacles
            if (checkNoCollisionWithObstacles(step)) {
                destinationCoordinates.y += step.y;
                destinationCoordinates.x += step.x;
                progress = 0f;
            }
            float newPlayerRotation = step.x != 0 ? -90f + step.x * 90f: step.y * 90f;
            rotation = newPlayerRotation;
        }
    }

    public boolean checkNoCollisionWithObstacles(GridPoint2 step) {
        GridPoint2 newCoordinates = coordinates;
        newCoordinates.x += step.x;
        newCoordinates.y += step.y;
        return !treeObstacle.getCoordinates().equals(newCoordinates);
    }
}

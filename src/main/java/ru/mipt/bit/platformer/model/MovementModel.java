package ru.mipt.bit.platformer.model;

import com.badlogic.gdx.math.GridPoint2;

//@Getter
public class MovementModel {
//    private GraphicModel graphics;
    private Direction direction;
//    Here will be some logic
    private GridPoint2 coordinates; // ???
//    private GridPoint2 destinationCoordinates; // ???
    private MovementLogic movementLogic;
    public MovementModel(GridPoint2 coordinates, GridPoint2 destinationCoordinates, float rotation, float progress) {
//        this.graphics = new GraphicModel(tankTexture, rectangle, textureRegion);
        this.direction = new Direction(rotation, destinationCoordinates);
        this.coordinates = coordinates;
//        this.destinationCoordinates = destinationCoordinates;
        movementLogic = new MovementLogic(progress);
    }
//    public Texture getTexture() {
//        return graphics.getTexture();
//    }
//    public Rectangle getRectangle() {
//        return graphics.getRectangle();
//    }
//    public TextureRegion getGraphics() {
//        return graphics.getTextureRegion();
//    }
    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    public float getProgress() {
        return movementLogic.getMoveProgress();
    }
    public float getRotation() {
        return direction.getRotation();
    }
    public GridPoint2 getDestinationCoordinates() {
        return direction.getDestinationCoordinates();
    }
    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.direction.setDestinationCoordinates(destinationCoordinates);
    }
    public void setProgress(float progress) {
        this.movementLogic.setMoveProgress(progress);
    }
    public void setRotation(float rotation) {
        direction.setRotation(rotation);
    }

}

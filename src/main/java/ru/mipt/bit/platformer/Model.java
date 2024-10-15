package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class Model {
    private Graphics graphics;
    private Direction direction;
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private float progress = 1f;
    public Model(Texture tankTexture, TextureRegion textureRegion, Rectangle rectangle, GridPoint2 coordinates, GridPoint2 destinationCoordinates, float rotation) {
        this.graphics = new Graphics(tankTexture, rectangle, textureRegion);

        this.direction = new Direction(new GridPoint2(destinationCoordinates.x - coordinates.x, destinationCoordinates.y - coordinates.y), rotation);
        this.coordinates = coordinates;
        this.destinationCoordinates = destinationCoordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public float getProgress() {
        return progress;
    }

    public Graphics getGraphics() {
        return graphics;
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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}

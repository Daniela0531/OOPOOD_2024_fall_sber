package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class Tank {
    private Texture texture;
    private TextureRegion graphics;
    private Rectangle rectangle;
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private float progress = 1f;
    private float rotation;
    public Tank(Texture tankTexture, TextureRegion graphics, Rectangle rectangle, GridPoint2 coordinates, GridPoint2 destinationCoordinates, float rotation) {
        this.texture = tankTexture;
        this.graphics = graphics;
        this.rectangle = rectangle;
        this.coordinates = coordinates;
        this.destinationCoordinates = destinationCoordinates;
        this.rotation = rotation;
    }
    public Texture getTexture() {
        return texture;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public TextureRegion getGraphics() {
        return graphics;
    }
    public GridPoint2 getCoordinates() {
        return coordinates;
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

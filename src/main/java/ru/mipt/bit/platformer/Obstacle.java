package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle {
    private Texture texture;
    private TextureRegion graphics;
    private GridPoint2 coordinates = new GridPoint2();
    private Rectangle rectangle = new Rectangle();

    public Obstacle(Texture texture, TextureRegion textureRegion, GridPoint2 coordinates, Rectangle rectangle) {
        this.texture = texture;
        graphics = textureRegion;
        this.coordinates = coordinates;
        this.rectangle = rectangle;
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

    public Texture getTexture() {
        return texture;
    }
}

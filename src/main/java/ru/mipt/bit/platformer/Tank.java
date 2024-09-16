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

    public Tank(Texture tankTexture, TextureRegion graphics, Rectangle rectangle, GridPoint2 coordinates) {
        this.texture = tankTexture;
        this.graphics = graphics;
        this.rectangle = rectangle;
        this.coordinates = coordinates;
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
}

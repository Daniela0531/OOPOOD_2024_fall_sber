package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

//@Getter

public class GraphicModel {
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;
    private GridPoint2 coordinates;

    public GraphicModel(Texture texture, Rectangle rectangle, TextureRegion textureRegion, GridPoint2 coordinates) {
        this.texture = texture;
        this.rectangle = rectangle;
        this.textureRegion = textureRegion;
        this.coordinates = coordinates;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
}
